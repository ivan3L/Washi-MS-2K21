package com.washi.businessservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.washi.businessservice.entity.LaundryServiceMaterial;
import com.washi.businessservice.entity.Order;
import com.washi.businessservice.service.impl.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
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
@RequestMapping("/business/order")
@Api(tags = "Business")
public class OrderServiceController {
    @Autowired
    OrderService orderService;

    @GetMapping
    @ApiOperation(value = "Retorna una lista de todas las ordenes")
    public ResponseEntity<List<Order>> listAllOrder(){
        List<Order> order = new ArrayList<>();
        order = orderService.findAll();
        if(order.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(order);
    }
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Retorna una determinada orden segun el id")
    public ResponseEntity<Order> getOrder(@PathVariable("id")Long id){
        Order order = orderService.getOrder(id);
        if(order == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(order);
    }

    @PostMapping
    @ApiOperation(value = "Crea una orden")
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order, BindingResult result){
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Order orderDB = orderService.createOrder(order);
        return  ResponseEntity.status( HttpStatus.CREATED).body(orderDB);
    }
    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Edita una orden")
    public ResponseEntity<?> updateOrder(@PathVariable("id") long id, @RequestBody Order order) {

        order.setId(id);
        Order currentOrder=orderService.updateOrder(order);

        if (currentOrder == null) {
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(currentOrder);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Borra una orden")
    public ResponseEntity<Order> deleteOrder(@PathVariable("id") long id) {
        Order order = orderService.getOrder(id);
        order = orderService.deleteOrder(order);
        return ResponseEntity.ok(order);
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
