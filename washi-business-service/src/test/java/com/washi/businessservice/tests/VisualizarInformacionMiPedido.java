package com.washi.businessservice.tests;

import com.washi.businessservice.entity.Order;
import com.washi.businessservice.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@SpringBootTest
public class VisualizarInformacionMiPedido {
    @Autowired
    private WebClient.Builder webClientBuilder;

    Order order;


    // Scenario: El washer desea visualizar un pedido que adquirio
    @Test
    void visualizarInformacionMiPedido() {

        // Given: Desea visualizar un pedido
        givenDeseaVisualizarUnPedido();
        // When : Ingresa al pedido
        whenIngresaAlPedido();
        // Then : Puede visualizar el pedido
        thenPuedeVisualizarElPedido();

    }

    private void givenDeseaVisualizarUnPedido() {
        User user = User.builder().name("Felipe").build();
        System.out.println(user);
    }

    private void whenIngresaAlPedido() {
        long idL = 1;
        String id = String.valueOf(idL);
        Order orderResponse = webClientBuilder.build()
                .get()
                .uri("http://localhost:8979/business/order/"+id)
                .retrieve()
                .bodyToMono(Order.class)
                .block();
        order=orderResponse;
    }

    private void thenPuedeVisualizarElPedido() {
        System.out.println(order);
    }
}
