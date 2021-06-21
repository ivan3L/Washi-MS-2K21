package com.washi.securityservice;

import com.washi.securityservice.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest
public class LogearseALaAplicacion {
    @Autowired
    private WebClient.Builder webClientBuilder;

    User user;

    // Scenario: Login Exitoso
    @Test
    void loginExitoso() {
        // Given: El usuario desea usar los servicios de la aplicación
        givenUsuarioDeseaUsarServiciosDeAplicacion();
        // When: realiza el login en la aplicacion
        whenRealizaElLoginEnLaAplicacion();
        // Then : Se realiza de manera correcta
        thenSeRealizaDeManeraCorrecta();
    }

    private void givenUsuarioDeseaUsarServiciosDeAplicacion() {
        user = new User();

    }

    private void whenRealizaElLoginEnLaAplicacion() {
        long idL = 1;
        String id = String.valueOf(idL);
        User userResponse = webClientBuilder.build()
                .get()
                .uri("http://localhost:8909/security/users/"+id)
                .retrieve()
                .bodyToMono(User.class)
                .block();
        user = userResponse;

    }

    private void thenSeRealizaDeManeraCorrecta() {
        System.out.println(user);
    }


}
