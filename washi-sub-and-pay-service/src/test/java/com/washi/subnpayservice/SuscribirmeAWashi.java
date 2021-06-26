package com.washi.subnpayservice;

import com.washi.subnpayservice.entity.Subscription;
import com.washi.subnpayservice.entity.UserPaymentMethod;
import com.washi.subnpayservice.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class SuscribirmeAWashi {
    @Autowired
    private WebClient.Builder webClientBuilder;

    Subscription subscription = new Subscription();
    List<Subscription> subscriptions = new ArrayList<>();
    User user;

    // Scenario : El washer desea visualizar sus planes de suscripción de la aplicación
    @Test
    void visualizarSuscripcion() {
        // Given : Desea ver suscripciones
        givenDeseaVisualizarSuscripciones();

        // When : Escoge su lista de suscripciones
        whenEscogeSuListaDeSuscripciones();

        // Then : Visualiza correctamente
        thenVisualizaCorrectamente();
    }

    private void givenDeseaVisualizarSuscripciones() {
        user = User.builder().name("Felipe").build();
        System.out.println(user);
    }

    private void whenEscogeSuListaDeSuscripciones() {
        List<Subscription> subscriptionsResponse = webClientBuilder.build()
                .get()
                .uri("http://localhost:8399/subnpay/subscriptions")
                .retrieve()
                .bodyToFlux(Subscription.class)
                .collectList()
                .block();
        subscriptions = subscriptionsResponse;
    }

    private void thenVisualizaCorrectamente() {
        System.out.println(subscriptions);
    }

    //Scenario: El washer desea ingresar una nuevo plan de suscripción a Washi
    @Test
    void ingresarNuevaSuscripcion() {

        //Given: El washer desea ingresar suscripción
        givenDeseaIngresarSuscripcion();

        //When:  Llena la nueva información
        whenLlenaLaNuevaInformacion();

        //Then:  Se suscribe correctamente
        thenSeSuscribeCorrectamente();
    }

    private void givenDeseaIngresarSuscripcion() {
        user = User.builder().name("Felipe").build();
        System.out.println(user);
    }

    private void whenLlenaLaNuevaInformacion() {
        subscription.setUser(user);
        Subscription subscriptionResponse = webClientBuilder.build()
                .post()
                .uri("http://localhost:8399/subnpay/subscriptions")
                .bodyValue(subscription)
                .retrieve()
                .bodyToMono(Subscription.class)
                .block();
        subscription = subscriptionResponse;
    }

    private void thenSeSuscribeCorrectamente() {
        System.out.println(subscription);
    }

    //Scenario: El washer desea actualizar uno de sus planes de suscripciones
    @Test
    void actualizarSuscripcion() {
        //Given: El washer desea actualizar suscripción
        givenDeseaActualizarSuscripcion();

        //When:  Actualiza la información
        whenActualiaSuscripcion();

        //Then: Se actualiza su suscripción
        thenSeActualizaSuSuscripcion();
    }

    private void givenDeseaActualizarSuscripcion() {
        user = User.builder().name("Felipe").build();
        System.out.println(user);
    }

    private void whenActualiaSuscripcion() {
        long idL = 1;
        String id = String.valueOf(idL);
        subscription.setUser(user);
        Subscription subscriptionResponse = webClientBuilder.build()
                .put()
                .uri("http://localhost:8399/subnpay/subscriptions/"+id)
                .bodyValue(subscription)
                .retrieve()
                .bodyToMono(Subscription.class)
                .block();
        subscription = subscriptionResponse;
    }

    private void thenSeActualizaSuSuscripcion() {
        System.out.println(subscription);
    }


}
