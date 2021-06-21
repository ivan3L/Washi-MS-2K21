package com.washi.geographicsservice;

import com.washi.geographicsservice.entity.Country;
import com.washi.geographicsservice.entity.Department;
import com.washi.geographicsservice.entity.District;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@SpringBootTest
public class FiltrarCatalogoDeLavanderias {
    @Autowired
    private WebClient.Builder webClientBuilder;
    District district = new District();

    // Scenario: El washer desea filtrar las lavanderias por distrito
    @Test
    void filtrarLavanderiasPorDistrito(){
        // Given: El washer desea filtrar por distrito
        givenDeseaFiltraPorDistrito();
        // When : Escoge uno de los distritos
        whenEscogeUnoDeLosDistritos();
        // Then : Le retorna el distrito seleccionado
        thenLeRetornaElDistritoSeleccionado();
    }

    private void givenDeseaFiltraPorDistrito() {
        long idL = 1L;
        String id = String.valueOf(idL);
        List<District> districts = webClientBuilder.build()
                .get()
                .uri("http://localhost:8090/geographics/districts?DepartmentId="+id)
                .retrieve()
                .bodyToFlux(District.class)
                .collectList()
                .block();
        System.out.println(districts);
    }


    private void whenEscogeUnoDeLosDistritos() {
        long idL = 1L;
        String id = String.valueOf(idL);
        District districtResponse = webClientBuilder.build()
                .get()
                .uri("http://localhost:8090/geographics/districts/" + id)
                .retrieve()
                .bodyToMono(District.class)
                .block();
        district=districtResponse;
    }

    private void thenLeRetornaElDistritoSeleccionado() {
        System.out.println(district);
    }
}
