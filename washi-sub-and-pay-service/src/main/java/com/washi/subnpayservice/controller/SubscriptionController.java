package com.washi.subnpayservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.washi.subnpayservice.entity.Subscription;
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

    @GetMapping
    @ApiOperation(value = "Retorna una lista de todos los suscripcions")
    public ResponseEntity<List<Subscription>> listAllSubscriptions(){
        List<Subscription> subscriptions = new ArrayList<>();
        subscriptions = subscriptionService.findAll();
        if(subscriptions.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(subscriptions);
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Retorna un suscripcion según su id")
    public ResponseEntity<Subscription> getSubscription(@PathVariable("id") Long id) {
        log.info("Fetching Subscription with id {}", id);
        Subscription subscription =  subscriptionService.getSubscription(id);
        if (null==subscription){
            log.error("Subscription with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(subscription);
    }

    @PostMapping
    @ApiOperation(value = "Crea un nuevo suscripcion")
    public ResponseEntity<Subscription> createSubscription(@Valid @RequestBody Subscription subscription, BindingResult result) {
        log.info("Creating Subscription : {}", subscription);
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Subscription subscriptionDB = subscriptionService.createSubscription (subscription);

        return  ResponseEntity.status( HttpStatus.CREATED).body(subscriptionDB);
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Modifica un suscripcion existente")
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

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Borra un suscripcion")
    public ResponseEntity<Subscription> deleteSubscription(@PathVariable("id") long id) {
        log.info("Fetching & Deleting Subscription with id {}", id);

        Subscription subscription = subscriptionService.getSubscription(id);
        if (null == subscription) {
            log.error("Unable to delete Subscription with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        subscription = subscriptionService.deleteSubscription(subscription);
        return ResponseEntity.ok(subscription);
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
