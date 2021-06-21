package com.washi.businessservice;

import com.washi.businessservice.entity.LaundryServiceMaterial;
import com.washi.businessservice.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@SpringBootTest
public class VisualizarServiciosLavanderia {

    @Autowired
    private WebClient.Builder webClientBuilder;

    LaundryServiceMaterial laundryServiceMaterial;

    // Scenario: El washer desea visualizar los servicios que ofrece una lavandería
    @Test
    void visualizarServiciosDeLavanderia() {

        // Given: El washer desea visualizar los servicios
        givenDeseaVisualizarLosServicios();
        // When : Selecciona la lavandería para visualizar
        whenSeleccionaLavanderiaParaVisualizar();
        // Then : Puede visualizar los servicios
        thenPuedeVisualizarLosServicios();

    }

    private void givenDeseaVisualizarLosServicios() {
        User user = User.builder().id(3L).name("Felipe").build();
        System.out.println(user);
    }

    private void whenSeleccionaLavanderiaParaVisualizar() {
        long idL = 1;
        String id = String.valueOf(idL);
        LaundryServiceMaterial laundryServiceMaterialResponse = webClientBuilder.build()
                .get()
                .uri("http://localhost:8979/business/laundry/"+id)
                .retrieve()
                .bodyToMono(LaundryServiceMaterial.class)
                .block();
        laundryServiceMaterial=laundryServiceMaterialResponse;
    }

    private void thenPuedeVisualizarLosServicios() {
        System.out.println(laundryServiceMaterial);
    }

    // Scenario: El washer desea visualizar los materiales que ofrece una lavandería
    @Test
    void visualizarMaterialesDeLavanderia(){

        // Given: El washer desea visualizar los materiales
        givenDeseaVisualizarLosMateriales();
        // When : Selecciona la lavandería para visualizar
        whenSeleccionaLavanderiaParaVisualizar();
        // Then : Puede visualizar los materiales
        thenPuedeVisualizarLosMateriales();

    }

    private void givenDeseaVisualizarLosMateriales() {
        User user = User.builder().id(3L).name("Felipe").build();
        System.out.println(user);
    }

    private void thenPuedeVisualizarLosMateriales() {
        System.out.println(laundryServiceMaterial);
    }

}
