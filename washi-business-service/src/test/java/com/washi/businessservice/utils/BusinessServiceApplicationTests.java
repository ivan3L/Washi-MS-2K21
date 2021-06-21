package com.washi.businessservice.utils;

import com.washi.businessservice.entity.LaundryServiceMaterial;
import com.washi.businessservice.entity.Order;
import com.washi.businessservice.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


@SpringBootTest
class BusinessServiceApplicationTests {

    @Autowired
    private WebClient.Builder webClientBuilder;

    List<LaundryServiceMaterial> laundryServiceMaterials;


    @Test
    void laundryServiceMaterials() {
        System.out.println(laundryServiceMaterials);
         laundryServiceMaterials = webClientBuilder.build()
                .get()
                .uri("http://localhost:8979/business/laundry")
                .retrieve()
                .bodyToFlux(LaundryServiceMaterial.class)
                .collectList()
                .block();
        System.out.println(laundryServiceMaterials);

    }



    @Test
    void newLaundryServiceMaterial() {
        LaundryServiceMaterial laundryServiceMaterial = new LaundryServiceMaterial();
        User user = User.builder().id(1L).name("Felipe").build();
        System.out.println(user);
        laundryServiceMaterial.setId(2L);
        laundryServiceMaterial.setUser(user);
        laundryServiceMaterial.setLaundryId(2L);
        laundryServiceMaterial.setServiceMaterialId(1L);
        laundryServiceMaterial.setDescriptions("Lavado profundo");
        laundryServiceMaterial.setDiscountPercentage(10.0F);
        laundryServiceMaterial.setRaiting(4.5F);
        LaundryServiceMaterial laundryServiceMaterialResponse = webClientBuilder.build()
                .post()
                .uri("http://localhost:8979/business/laundry")
                .bodyValue(laundryServiceMaterial)
                .retrieve()
                .bodyToMono(LaundryServiceMaterial.class)
                .block();
        System.out.println(laundryServiceMaterialResponse);
    }


    @Test
    void laundryServiceMaterialById() {
        long idL = 1;
        String id = String.valueOf(idL);
        LaundryServiceMaterial laundryServiceMaterial = webClientBuilder.build()
                .get()
                .uri("http://localhost:8979/business/laundry/"+id)
                .retrieve()
                .bodyToMono(LaundryServiceMaterial.class)
                .block();
        System.out.println(laundryServiceMaterial);
    }

    @Test
    void updateLaundryServiceMaterial() {
        long idL = 1;
        String id = String.valueOf(idL);
        LaundryServiceMaterial laundryServiceMaterial = new LaundryServiceMaterial();
        LaundryServiceMaterial laundryServiceMaterialResponse = webClientBuilder.build()
                .put()
                .uri("http://localhost:8979/business/laundry/"+id)
                .bodyValue(laundryServiceMaterial)
                .retrieve()
                .bodyToMono(LaundryServiceMaterial.class)
                .block();
        System.out.println(laundryServiceMaterialResponse);
    }


    @Test
    void deleteLaundryServiceMaterial() {
        long idL = 1;
        String id = String.valueOf(idL);
        LaundryServiceMaterial laundryServiceMaterial = webClientBuilder.build()
                .delete()
                .uri("http://localhost:8979/business/laundry/"+id)
                .retrieve()
                .bodyToMono(LaundryServiceMaterial.class)
                .block();
        System.out.println(laundryServiceMaterial);
    }



    @Test
    void orders() {
        List<Order> orders = webClientBuilder.build()
                .get()
                .uri("http://localhost:8979/business/order")
                .retrieve()
                .bodyToFlux(Order.class)
                .collectList()
                .block();
        System.out.println(orders);
    }



    @Test
    void newOrder() {
        Order order = new Order();
        User user = User.builder().id(12L).name("Felipe").build();
        System.out.println(user);
        Order orderResponse = webClientBuilder.build()
                .post()
                .uri("http://localhost:8979/business/order")
                .bodyValue(order)
                .retrieve()
                .bodyToMono(Order.class)
                .block();
        System.out.println(orderResponse);
    }


    @Test
    void orderById() {
        long idL = 1L;
        String id = String.valueOf(idL);
        Order order = webClientBuilder.build()
                .get()
                .uri("http://localhost:8979/business/order/"+id)
                .retrieve()
                .bodyToMono(Order.class)
                .block();
        System.out.println(order);
    }


    @Test
    void updateOrder() {
        long idL = 1;
        String id = String.valueOf(idL);
        Order order = new Order();
        Order orderResponse = webClientBuilder.build()
                .put()
                .uri("http://localhost:8979/business/order/"+id)
                .bodyValue(order)
                .retrieve()
                .bodyToMono(Order.class)
                .block();
        System.out.println(orderResponse);
    }


    @Test
    void deleteOrder() {
        long idL = 1;
        String id = String.valueOf(idL);
        Order order = webClientBuilder.build()
                .delete()
                .uri("http://localhost:8979/business/order/"+id)
                .retrieve()
                .bodyToMono(Order.class)
                .block();
        System.out.println(order);
    }

}
