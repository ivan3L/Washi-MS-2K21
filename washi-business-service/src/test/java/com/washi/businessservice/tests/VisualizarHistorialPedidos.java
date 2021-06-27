package com.washi.businessservice.tests;

import com.washi.businessservice.entity.Order;
import com.washi.businessservice.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@SpringBootTest
public class VisualizarHistorialPedidos {

    @Autowired
    private WebClient.Builder webClientBuilder;

    List<Order> orders;

    // Scenario: El washer desea visualizar su historial de pedidos
    @Test
    void visualizarHistorialPedidos() {

        // Given: Desea visualizar historial de pedidos
        givenDeseaVisualizarSuHistorialPedidos();
        // When : Ingresa a la sección de pedidos
        whenIngresaASeccionDePedidos();
        // Then : Puede visualizar los pedidos
        thenPuedeVisualizarLosPedidos();

    }

    private void givenDeseaVisualizarSuHistorialPedidos() {
        User user = User.builder().id(3L).name("Jose").build();
        System.out.println(user);
    }

    private void whenIngresaASeccionDePedidos() {
        List<Order> ordersResponse = webClientBuilder.build()
                .get()
                .uri("http://localhost:8979/business/order")
                .retrieve()
                .bodyToFlux(Order.class)
                .collectList()
                .block();
        orders=ordersResponse;
    }

    private void thenPuedeVisualizarLosPedidos() {
        System.out.println(orders);
    }
}
