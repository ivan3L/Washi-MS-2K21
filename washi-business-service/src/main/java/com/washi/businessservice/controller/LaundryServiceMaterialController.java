package com.washi.businessservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.washi.businessservice.entity.LaundryServiceMaterial;
import com.washi.businessservice.service.impl.LaundryServiceMaterialService;
import io.swagger.annotations.Api;
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
@RequestMapping("/business/laundry")
@Api(tags = "Business")
public class LaundryServiceMaterialController {
    @Autowired
    LaundryServiceMaterialService laundryServiceMaterialService;

    @GetMapping
    public ResponseEntity<List<LaundryServiceMaterial>> listAllLaundryServiceMaterial(){
        List<LaundryServiceMaterial> laundryServiceMaterials = new ArrayList<>();
        laundryServiceMaterials = laundryServiceMaterialService.findAll();
        if(laundryServiceMaterials.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(laundryServiceMaterials);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<LaundryServiceMaterial> getLaundryServiceMaterial(@PathVariable("id") Long id) {
        log.info("Fetching User with id {}", id);
        LaundryServiceMaterial laundryServiceMaterial =  laundryServiceMaterialService.getLaundryServiceMaterial(id);
        if (null==laundryServiceMaterial){
            log.error("User with id {} not found.", id);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(laundryServiceMaterial);
    }

    @PostMapping
    public ResponseEntity<LaundryServiceMaterial> createLaundryServiceMaterial(@Valid @RequestBody LaundryServiceMaterial laundryServiceMaterial, BindingResult result) {
        log.info("Creating User : {}", laundryServiceMaterial);
        if (result.hasErrors()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        LaundryServiceMaterial laundryServiceMaterialDB = laundryServiceMaterialService.createLaundryServiceMaterial (laundryServiceMaterial);

        return  ResponseEntity.status( HttpStatus.CREATED).body(laundryServiceMaterialDB);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateLaundryServiceMaterial(@PathVariable("id") long id, @RequestBody LaundryServiceMaterial laundryServiceMaterial) {
        log.info("Updating User with id {}", id);

        laundryServiceMaterial.setId(id);
        LaundryServiceMaterial currentLaundryServiceMaterial=laundryServiceMaterialService.updateLaundryServiceMaterial(laundryServiceMaterial);

        if (currentLaundryServiceMaterial == null) {
            log.error("Unable to update. User with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(currentLaundryServiceMaterial);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<LaundryServiceMaterial> deleteLaundryServiceMaterial(@PathVariable("id") long id) {
        log.info("Fetching & Deleting User with id {}", id);

        LaundryServiceMaterial laundryServiceMaterial = laundryServiceMaterialService.getLaundryServiceMaterial(id);
        if (null == laundryServiceMaterial) {
            log.error("Unable to delete User with id {} not found.", id);
            return  ResponseEntity.notFound().build();
        }
        laundryServiceMaterial = laundryServiceMaterialService.deleteLaundryServiceMaterial(laundryServiceMaterial);
        return ResponseEntity.ok(laundryServiceMaterial);
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
