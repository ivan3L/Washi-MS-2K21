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
public class UtilizarMetodoDePago {
    @Autowired
    private WebClient.Builder webClientBuilder;

    UserPaymentMethod userPaymentMethod = new UserPaymentMethod();
    List<UserPaymentMethod> userPaymentMethods = new ArrayList<>();
    User user;

    //Scenario: El washer desea visualizar sus método de pago de la aplicación
    @Test
    void visualizarMetodoDePago() {
        //Given: El washer desea ver métodos de pago
        givenDeseaVisualizarMetodosDePago();

        //When: Escoge su lista de métodos de pago
        whenEscogeSuListaDeMetodosDePago();

        //Then: El washer visualiza correctamente los métodos
        thenVisualizaCorrectamenteLosMetodos();
    }

    private void givenDeseaVisualizarMetodosDePago() {
        user = User.builder().name("Felipe").build();
        System.out.println(user);
    }

    private void whenEscogeSuListaDeMetodosDePago() {
        List<UserPaymentMethod> userPaymentMethodsResponse = webClientBuilder.build()
                .get()
                .uri("http://localhost:8399/subnpay/paymentmethods")
                .retrieve()
                .bodyToFlux(UserPaymentMethod.class)
                .collectList()
                .block();
        userPaymentMethods=userPaymentMethodsResponse;
    }

    private void thenVisualizaCorrectamenteLosMetodos() {
        System.out.println(userPaymentMethods);
    }


    //Scenario: El washer desea ingresar un nuevo método con cual realizar pagos
    @Test
    void ingresarNuevoMetodoDePago() {
        //Given: El washer desea ingresar método de pago
        givenDeseaIngresarMetodoDePago();

        //When:  Llena la nueva información
        whenLlenaLaNuevaInformacion();

        //Then: Se ingresa su nuevo método correctamente
        thenSeIngresaSuNuevoMetodoCorrectamente();
    }

    private void givenDeseaIngresarMetodoDePago() {
        user = User.builder().name("Felipe").build();
        System.out.println(user);
    }

    private void whenLlenaLaNuevaInformacion() {
    }

    private void thenSeIngresaSuNuevoMetodoCorrectamente() {
        System.out.println(userPaymentMethod);
    }

    //Scenario: El washer desea actualizar uno de sus métodos de pago
    @Test
    void actualizarMetodoDePago() {
        //Given: El washer desea actualizar método de pago
        givenDeseaActualizarMetodoDePago();

        //When:  Actualiza la información
        whenActualizaLaInformacion();

        //Then: Se actualiza su método correctamente
        thenSeActualizaSuMetodoDePago();
    }

    private void givenDeseaActualizarMetodoDePago() {
        user = User.builder().name("Felipe").build();
        System.out.println(user);
    }

    private void whenActualizaLaInformacion() {
    }

    private void thenSeActualizaSuMetodoDePago() {
        System.out.println(userPaymentMethod);
    }


    //Scenario: El washer desea eliminar uno de sus métodos de pago
    @Test
    void eliminarMetodoDePago() {
        //Given: El washer desea eliminar método de pago
        givenDeseaEliminarMetodoDePago();

        //When:  Selecciona método a eliminar
        whenSeleccionaMetodoAEliminar();

        //Then: Se elimina correctamente
        thenSeEliminaCorrectamente();
    }

    private void givenDeseaEliminarMetodoDePago() {
        user = User.builder().name("Felipe").build();
        System.out.println(user);
    }

    private void whenSeleccionaMetodoAEliminar() {
    }

    private void thenSeEliminaCorrectamente() {
        System.out.println(userPaymentMethod);
    }

}
