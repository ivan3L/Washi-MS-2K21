package com.washi.securityservice.utils;

import com.washi.securityservice.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@SpringBootTest
class SecurityServiceApplicationTests {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Test
    void users() {
        List<User> users = webClientBuilder.build()
                .get()
                .uri("http://localhost:8909/security/users")
                .retrieve()
                .bodyToFlux(User.class)
                .collectList()
                .block();
        System.out.println(users);
    }

    @Test
    void newUser() {
        User user = new User();
        User userResponse = webClientBuilder.build()
                .post()
                .uri("http://localhost:8909/security/users")
                .bodyValue(user)
                .retrieve()
                .bodyToMono(User.class)
                .block();
        System.out.println(userResponse);
    }

    @Test
    void userById() {
        long idL = 1;
        String id = String.valueOf(idL);
        User user = webClientBuilder.build()
                .get()
                .uri("http://localhost:8909/security/users/"+id)
                .retrieve()
                .bodyToMono(User.class)
                .block();
        System.out.println(user);
    }

    @Test
    void updateUser() {
        long idL = 1;
        String id = String.valueOf(idL);
        User user = new User();
        User userResponse = webClientBuilder.build()
                .put()
                .uri("http://localhost:8909/security/users/"+id)
                .bodyValue(user)
                .retrieve()
                .bodyToMono(User.class)
                .block();
        System.out.println(userResponse);
    }

    @Test
    void deleteUser() {
        long idL = 1;
        String id = String.valueOf(idL);
        User user = webClientBuilder.build()
                .delete()
                .uri("http://localhost:8909/security/users/"+id)
                .retrieve()
                .bodyToMono(User.class)
                .block();
        System.out.println(user);
    }

}
