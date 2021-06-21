package com.washi.subnpayservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.washi.subnpayservice.entity.Subscription;
import com.washi.subnpayservice.model.User;
import com.washi.subnpayservice.service.SubscriptionService;
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
@RequestMapping("/subnpay/subscriptions")
@Api(tags = "Subnpay")
public class SubscriptionController {
    @Autowired
    SubscriptionService subscriptionService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retorna una lista de las subscripciones de todos los usuarios")
    public ResponseEntity<List<Subscription>> fetchAll() {
        try {
            List<Subscription> subscriptions = subscriptionService.findAll();
            return new ResponseEntity<List<Subscription>>(subscriptions, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Retorna una subscripción de un usuario segun su id")
    public ResponseEntity<Subscription> fetchById(@PathVariable("id") Long id) {
        try {
            Optional<Subscription> optionalSubscription = subscriptionService.findById(id);
            if(optionalSubscription.isPresent()) {
                return new ResponseEntity<Subscription>(optionalSubscription.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /*
    @GetMapping(path = "/user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Subscription> fetchByUserId(@PathVariable("userId") String userId) {
        try {
            List<Optional<Subscription>> optionalSubscription = subscriptionService.findByUserId(userId);
            if(optionalSubscription != null) {
                return ResponseEntity.ok(optionalSubscription.zdzdvvadsvad);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch( Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */
    @PostMapping
    @ApiOperation(value = "Crea una subcripción")
    public ResponseEntity<Subscription> createSubscription(@Valid @RequestBody Subscription subscription, BindingResult result) {
        log.info("Creating Subscription : {}", subscription);
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Subscription subscriptionDB = subscriptionService.createSubscription(subscription);

        return  ResponseEntity.status( HttpStatus.CREATED).body(subscriptionDB);
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Edita una subcripción")
    public ResponseEntity<?> updateSubscription(@PathVariable("id") long id, @RequestBody Subscription subscription) {
        log.info("Updating Subscription with id {}", id);

        subscription.setId(id);
        Subscription currentSubscription=subscriptionService.updateSubscription(subscription);

        if (currentSubscription == null) {
            log.error("Unable to update. Subscription with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(currentSubscription);
    }
    /*
        @DeleteMapping(value = "/{id}")
        public ResponseEntity<Subscription> deleteSubscription(@PathVariable("id") Long id) {
            log.info("Fetching & Deleting Subscription with id {}", id);
            Optional<Subscription> optionalSubscription = subscriptionService.findById(id.toString());
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
