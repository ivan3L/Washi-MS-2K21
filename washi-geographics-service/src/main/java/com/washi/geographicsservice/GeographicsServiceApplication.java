package com.washi.geographicsservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class GeographicsServiceApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(GeographicsServiceApplication.class, args);
    }
}
