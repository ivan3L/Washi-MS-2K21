package com.washi.businessservice.tests;

import com.washi.businessservice.entity.Order;
import com.washi.businessservice.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@SpringBootTest
public class VisualizarInformacionPedidos {
    @Autowired
    private WebClient.Builder webClientBuilder;

    Order order;

    List<Order> orders;

    // Scenario: El dueño desea visualizar información detallada de un pedido
    @Test
    void visualizarInformacionPedidos() {

        // Given: Desea visualizar un pedido
        givenDeseaVisualizarSusPedidos();
        // When : Ingresa al pedido
        whenIngresaAlPedido();
        // Then : Puede visualizar el pedido
        thenPuedeVisualizarElPedido();

    }

    private void givenDeseaVisualizarSusPedidos() {
        User user = User.builder().id(3L).name("Jose").build();
        System.out.println(user);
    }

    private void whenIngresaAlPedido() {
        List<Order> ordersResponse = webClientBuilder.build()
                .get()
                .uri("http://localhost:8979/business/order")
                .retrieve()
                .bodyToFlux(Order.class)
                .collectList()
                .block();
        orders=ordersResponse;
    }

    private void thenPuedeVisualizarElPedido() {
        System.out.println(orders);
    }


}
