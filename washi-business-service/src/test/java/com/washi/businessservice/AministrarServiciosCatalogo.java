package com.washi.businessservice;

import com.washi.businessservice.entity.LaundryServiceMaterial;
import com.washi.businessservice.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootTest
public class AministrarServiciosCatalogo {

    @Autowired
    private WebClient.Builder webClientBuilder;

    LaundryServiceMaterial laundryServiceMaterial = new LaundryServiceMaterial();
    User user;

    // Scenario: El dueño de la lavandería quiere agregar un servicio
    @Test
    void agregarServicio(){
        // Given: El dueño de lavandería desea agregar un nuevo servicio
        givenDeseaAgregarUnNuevoServicio();
        // When: Elige el servicio y agrega
        whenEligeElServicioYAgrega();
        // Then: El servicio se agrega exitosamente
        thenElServicioSeAgregaExitosamente();
    }

    private void givenDeseaAgregarUnNuevoServicio() {
        user = User.builder().name("Jose").build();
        System.out.println(user);
    }

    private void whenEligeElServicioYAgrega() {
        laundryServiceMaterial.setUser(user);
        LaundryServiceMaterial laundryServiceMaterialResponse = webClientBuilder.build()
                .post()
                .uri("http://localhost:8979/business/laundry")
                .bodyValue(laundryServiceMaterial)
                .retrieve()
                .bodyToMono(LaundryServiceMaterial.class)
                .block();
        laundryServiceMaterial=laundryServiceMaterialResponse;
    }

    private void thenElServicioSeAgregaExitosamente() {
        System.out.println(laundryServiceMaterial);
    }

    // Scenario: El dueño de la lavandería quiere eliminar un servicio
    @Test
    void eliminarServicio(){
        // Given: El dueño de lavandería desea eliminar un nuevo servicio
        givenDeseaEliminarUnNuevoServicio();
        // When: Elige el servicio y borra
        whenEligeElServicioYBorra();
        // Then: El servicio se elimina exitosamente
        thenElServicioSeEliminaExitosamente();
    }

    private void givenDeseaEliminarUnNuevoServicio() {
        user = User.builder().name("Jose").build();
        System.out.println(user);
    }

    private void whenEligeElServicioYBorra() {
        long idL = 1;
        String id = String.valueOf(idL);
        laundryServiceMaterial.setUser(user);
        LaundryServiceMaterial laundryServiceMaterialResponse = webClientBuilder.build()
                .delete()
                .uri("http://localhost:8979/business/laundry/"+id)
                .retrieve()
                .bodyToMono(LaundryServiceMaterial.class)
                .block();
        laundryServiceMaterial = laundryServiceMaterialResponse;
    }

    private void thenElServicioSeEliminaExitosamente() {System.out.println(laundryServiceMaterial);
    }

    // Scenario: El dueño de la lavandería quiere editar un servicio
    @Test
    void editarServicio(){
        // Given: El dueño de lavandería desea editar un nuevo servicio
        givenDeseaEditarUnNuevoServicio();
        // When: Elige el servicio y edita
        whenEligeElServicioYEdita();
        // Then: El servicio se edita exitosamente
        thenElServicioSeEditaExitosamente();
    }

    private void givenDeseaEditarUnNuevoServicio() {
        user = User.builder().name("Felipe").build();
        System.out.println(user);
    }

    private void whenEligeElServicioYEdita() {
        long idL = 1;
        String id = String.valueOf(idL);
        laundryServiceMaterial.setUser(user);
        LaundryServiceMaterial laundryServiceMaterialResponse = webClientBuilder.build()
                .put()
                .uri("http://localhost:8979/business/laundry/"+id)
                .bodyValue(laundryServiceMaterial)
                .retrieve()
                .bodyToMono(LaundryServiceMaterial.class)
                .block();
        laundryServiceMaterial=laundryServiceMaterialResponse;
    }

    private void thenElServicioSeEditaExitosamente() {System.out.println(laundryServiceMaterial);

    }

}
