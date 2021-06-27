package com.washi.subnpayservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.washi.subnpayservice.entity.UserPaymentMethod;
import com.washi.subnpayservice.entity.UserPaymentMethod;
import com.washi.subnpayservice.model.User;
import com.washi.subnpayservice.service.UserPaymentMethodService;
import com.washi.subnpayservice.service.UserPaymentMethodService;
//import io.swagger.annotations.Api;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/subnpay/userPaymentMethods")
@Api(tags = "Subnpay")
public class UserPaymentMethodController {
    @Autowired
    UserPaymentMethodService userPaymentMethodService;

    @GetMapping
    @ApiOperation(value = "Retorna una lista de todos los userPaymentMethods")
    public ResponseEntity<List<UserPaymentMethod>> listAllUserPaymentMethods(){
        List<UserPaymentMethod> userPaymentMethods = new ArrayList<>();
        userPaymentMethods = userPaymentMethodService.findAll();
        if(userPaymentMethods.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(userPaymentMethods);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Retorna un userPaymentMethod según su id")
    public ResponseEntity<UserPaymentMethod> getUserPaymentMethod(@PathVariable("id") Long id) {
        log.info("Fetching UserPaymentMethod with id {}", id);
        UserPaymentMethod userPaymentMethod =  userPaymentMethodService.getUserPaymentMethod(id);
        if (null==userPaymentMethod){
            log.error("UserPaymentMethod with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userPaymentMethod);
    }

    @PostMapping
    @ApiOperation(value = "Crea un nuevo userPaymentMethod")
    public ResponseEntity<UserPaymentMethod> createUserPaymentMethod(@Valid @RequestBody UserPaymentMethod userPaymentMethod, BindingResult result) {
        log.info("Creating UserPaymentMethod : {}", userPaymentMethod);
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        UserPaymentMethod userPaymentMethodDB = userPaymentMethodService.createUserPaymentMethod (userPaymentMethod);

        return  ResponseEntity.status( HttpStatus.CREATED).body(userPaymentMethodDB);
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Modifica un userPaymentMethod existente")
    public ResponseEntity<?> updateUserPaymentMethod(@PathVariable("id") long id, @RequestBody UserPaymentMethod userPaymentMethod) {
        log.info("Updating UserPaymentMethod with id {}", id);

        userPaymentMethod.setId(id);
        UserPaymentMethod currentUserPaymentMethod=userPaymentMethodService.updateUserPaymentMethod(userPaymentMethod);

        if (currentUserPaymentMethod == null) {
            log.error("Unable to update. UserPaymentMethod with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(currentUserPaymentMethod);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Borra un userPaymentMethod")
    public ResponseEntity<UserPaymentMethod> deleteUserPaymentMethod(@PathVariable("id") long id) {
        log.info("Fetching & Deleting UserPaymentMethod with id {}", id);

        UserPaymentMethod userPaymentMethod = userPaymentMethodService.getUserPaymentMethod(id);
        if (null == userPaymentMethod) {
            log.error("Unable to delete UserPaymentMethod with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        userPaymentMethod = userPaymentMethodService.deleteUserPaymentMethod(userPaymentMethod);
        return ResponseEntity.ok(userPaymentMethod);
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
