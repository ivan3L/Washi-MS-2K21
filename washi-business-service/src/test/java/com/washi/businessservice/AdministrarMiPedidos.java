package com.washi.businessservice;

import com.washi.businessservice.entity.Order;
import com.washi.businessservice.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest
public class AdministrarMiPedidos {
    @Autowired
    private WebClient.Builder webClientBuilder;

    Order order = new Order();
    User user;

    // Scenario: El washer desea modificar un pedido ya realizado
    @Test
    void modificarPedido(){
        // Given: El washer desea modificar un pedido ya realizado
        givenDeseaModificarPedidoRealizado();

        // When : Escoge el pedido a modificar y lo modifica
        whenEscogeYModificaPedido();

        // Then : El pedido se modifica
        thenPedidoSeModifica();
    }

    private void givenDeseaModificarPedidoRealizado() {
        user = User.builder().name("Felipe").build();
        System.out.println(user);
    }

    private void whenEscogeYModificaPedido() {
        long idL = 1;
        String id = String.valueOf(idL);
        order.setUser(user);
        Order orderResponse = webClientBuilder.build()
                .put()
                .uri("http://localhost:8979/business/order/"+id)
                .bodyValue(order)
                .retrieve()
                .bodyToMono(Order.class)
                .block();
        order = orderResponse;
    }

    private void thenPedidoSeModifica() {
        System.out.println(order);
    }

    // Scenario: El washer desea cancelar un pedido ya realizado
    @Test
    void cancelarPedido(){
        // Given: El washer desea cancelar pedido
        givenDeseaCancelarPedido();

        // When : Escoge el pedido a cancelar
        whenEscogePedidoACancelar();

        // Then : El pedido se cancela correctamente
        thenPedidoCancelaCorrectamente();
    }

    private void givenDeseaCancelarPedido() {
         user = User.builder().name("Felipe").build();
        System.out.println(user);
    }

    private void whenEscogePedidoACancelar() {
        long idL = 1;
        String id = String.valueOf(idL);
        order.setUser(user);
        Order orderResponse = webClientBuilder.build()
                .delete()
                .uri("http://localhost:8979/business/order/"+id)
                .retrieve()
                .bodyToMono(Order.class)
                .block();
        order=orderResponse;
    }

    private void thenPedidoCancelaCorrectamente() {
        System.out.println(order);
    }


}
