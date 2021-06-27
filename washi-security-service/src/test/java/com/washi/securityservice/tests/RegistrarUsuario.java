package com.washi.securityservice.tests;

import com.washi.securityservice.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest
public class RegistrarUsuario {

    @Autowired
    private WebClient.Builder webClientBuilder;

    User user = new User();

    // Scenario: Se quiere registrar con correo
    @Test
    void registrarConCorreo(){
        // Given : El washer quiere usar correo personal para registrarse
        givenQuiereUsarCorreoPersonalParaRegistrarse();
        // When : se llenan los datos de registro de cuenta
        whenLlenaLosDatosDeRegistroDeCuenta();
        // Then : es posible registrarse
        thenEsPosibleRegistrarse();
    }

    private void givenQuiereUsarCorreoPersonalParaRegistrarse() {
        user.setEmail("prueba@fas.com");

    }

    private void whenLlenaLosDatosDeRegistroDeCuenta() {
        user.setPassword("ezpass");
        User userResponse = webClientBuilder.build()
                .post()
                .uri("http://localhost:8909/security/users")
                .bodyValue(user)
                .retrieve()
                .bodyToMono(User.class)
                .block();
        user = userResponse;
    }

    private void thenEsPosibleRegistrarse() {
        System.out.println(user);
    }


}
