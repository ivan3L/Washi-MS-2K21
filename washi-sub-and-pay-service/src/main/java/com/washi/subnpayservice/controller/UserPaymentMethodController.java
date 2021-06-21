package com.washi.subnpayservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.washi.subnpayservice.entity.UserPaymentMethod;
import com.washi.subnpayservice.model.User;
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
@RequestMapping("/subnpay/paymentmethods")
@Api(tags = "Subnpay")
public class UserPaymentMethodController {
    @Autowired
    UserPaymentMethodService userPaymentMethodService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retorna una lista de todos los metodos de pagos de los usuarios")
    public ResponseEntity<List<UserPaymentMethod>> fetchAll() {
        try {
            List<UserPaymentMethod> userPaymentMethods = userPaymentMethodService.findAll();
            return new ResponseEntity<List<UserPaymentMethod>>(userPaymentMethods, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retorna los metodos de pagos de un usuario segun su id")
    public ResponseEntity<UserPaymentMethod> fetchById(@PathVariable("id") Long id) {
        try {
            Optional<UserPaymentMethod> optionalUserPaymentMethod = userPaymentMethodService.findById(id);
            if(optionalUserPaymentMethod.isPresent()) {
                return new ResponseEntity<UserPaymentMethod>(optionalUserPaymentMethod.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /*
    @GetMapping(path = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserPaymentMethod> fetchByUserId(@PathVariable("userId") String userId) {
        try {
            List<Optional<UserPaymentMethod>> optionalUserPaymentMethod = userPaymentMethodService.findByUserId(userId);
            if(optionalUserPaymentMethod != null) {
                return ResponseEntity.ok(optionalUserPaymentMethod.zdzdvvadsvad);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */
    @PostMapping
    @ApiOperation(value = "Crea un metodo de pago para un usuario")
    public ResponseEntity<UserPaymentMethod> createUserPaymentMethod(@Valid @RequestBody UserPaymentMethod userPaymentMethod, BindingResult result) {
        log.info("Creating UserPaymentMethod : {}", userPaymentMethod);
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        UserPaymentMethod userPaymentMethodDB = userPaymentMethodService.createUserPaymentMethod(userPaymentMethod);

        return  ResponseEntity.status( HttpStatus.CREATED).body(userPaymentMethodDB);
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Edita un metodo de pago de un usuario")
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
/*
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserPaymentMethod> deleteUserPaymentMethod(@PathVariable("id") Long id) {
        log.info("Fetching & Deleting UserPaymentMethod with id {}", id);
        Optional<UserPaymentMethod> optionalUserPaymentMethod = userPaymentMethodService.findById(id.toString());
        if (null == user) {
            log.error("Unable to delete User with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        user = userService.deleteUser(user);
        return ResponseEntity.ok(user);
    }
*/
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
