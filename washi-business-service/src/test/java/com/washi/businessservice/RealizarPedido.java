package com.washi.businessservice;

import com.washi.businessservice.entity.LaundryServiceMaterial;
import com.washi.businessservice.entity.Order;
import com.washi.businessservice.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@SpringBootTest
public class RealizarPedido {
    @Autowired
    private WebClient.Builder webClientBuilder;

    Order order = new Order();
    User user;

    // Scenario: El Washer quiere solicitar un servicio a su pedido
    @Test
    void solicitarPedido(){
        // Given: El washer desea añadir un pedido
        givenDeseaAñadirUnPedido();

        // When : Se añade el pedido a su lista
        whenSeAñadeElPedidoASuLista();

        // Then : El pedido se añade correctamente
        thenSeAñadeCorrectamente();
    }
    private void givenDeseaAñadirUnPedido() {
         user = User.builder().name("Felipe").build();
        System.out.println(user);
    }

    private void whenSeAñadeElPedidoASuLista() {
        order.setUser(user);
        Order orderResponse = webClientBuilder.build()
                .post()
                .uri("http://localhost:8979/business/order")
                .bodyValue(order)
                .retrieve()
                .bodyToMono(Order.class)
                .block();
        order = orderResponse;
    }

    private void thenSeAñadeCorrectamente() {
        System.out.println(order);

    }




    // Scenario: El washer desea remover un servicio de su pedido
    @Test
    void removerPedido(){
        // Given: El washer desea remover un pedido
        givenDeseaRemoverUnPedido();
        // When : Se quita el pedido de su lista
        whenSeQuitaElPedidoASuLista();

        // Then : El pedido se remueve correctamente
        thenSeRemueveCorrectamente();
    }

    private void givenDeseaRemoverUnPedido() {
        User user = User.builder().name("Felipe").build();
        System.out.println(user);
    }

    private void whenSeQuitaElPedidoASuLista() {
        long idL = 1;
        String id = String.valueOf(idL);
        Order orderResponse = webClientBuilder.build()
                .delete()
                .uri("http://localhost:8979/business/order/"+id)
                .retrieve()
                .bodyToMono(Order.class)
                .block();
        order = orderResponse;
    }

    private void thenSeRemueveCorrectamente() {
        System.out.println(order);
    }
}
