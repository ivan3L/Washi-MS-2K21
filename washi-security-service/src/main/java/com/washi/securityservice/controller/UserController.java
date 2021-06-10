package com.washi.securityservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.washi.securityservice.entity.User;
import com.washi.securityservice.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/security/users")
@Api(tags = "Security")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    @ApiOperation(value = "Retorna una lista de todos los usuarios")
    public ResponseEntity<List<User>> listAllUsers(){
        List<User> users = new ArrayList<>();
        users = userService.findAll();
        if(users.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(users);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Retorna un usuario según su id")
    public ResponseEntity<User> getUser(@PathVariable("id") Long id) {
        log.info("Fetching User with id {}", id);
        User user =  userService.getUser(id);
        if (null==user){
            log.error("User with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    @ApiOperation(value = "Crea un nuevo usuario")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user, BindingResult result) {
        log.info("Creating User : {}", user);
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        User userDB = userService.createUser (user);

        return  ResponseEntity.status( HttpStatus.CREATED).body(userDB);
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Modifica un usuario existente")
    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
        log.info("Updating User with id {}", id);

        user.setId(id);
        User currentUser=userService.updateUser(user);

        if (currentUser == null) {
            log.error("Unable to update. User with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(currentUser);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Borra un usuario")
    public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
        log.info("Fetching & Deleting User with id {}", id);

        User user = userService.getUser(id);
        if (null == user) {
            log.error("Unable to delete User with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        user = userService.deleteUser(user);
        return ResponseEntity.ok(user);
    }

    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
                .map(err ->{
                    Map<String,String> error =  new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString="";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

}
