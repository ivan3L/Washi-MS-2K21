package com.washi.subnpayservice.utils;

import com.washi.subnpayservice.entity.Subscription;
import com.washi.subnpayservice.entity.UserPaymentMethod;
import com.washi.subnpayservice.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@SpringBootTest
class SecurityServiceApplicationTests {

    @Autowired
    private WebClient.Builder webClientBuilder;



    @Test
    void paymentMethods() {
        List<UserPaymentMethod> userPaymentMethods = webClientBuilder.build()
                .get()
                .uri("http://localhost:8399/subnpay/userPaymentMethods")
                .retrieve()
                .bodyToFlux(UserPaymentMethod.class)
                .collectList()
                .block();
        System.out.println(userPaymentMethods);
    }

    @Test
    void newPaymentMethod() {
        UserPaymentMethod userPaymentMethod = new UserPaymentMethod();
        UserPaymentMethod userPaymentMethodResponse = webClientBuilder.build()
                .post()
                .uri("http://localhost:8399/subnpay/userPaymentMethods")
                .bodyValue(userPaymentMethod)
                .retrieve()
                .bodyToMono(UserPaymentMethod.class)
                .block();
        System.out.println(userPaymentMethodResponse);
    }

    @Test
    void userPaymentMethodById() {
        long idL = 1;
        String id = String.valueOf(idL);
        UserPaymentMethod userPaymentMethod = webClientBuilder.build()
                .get()
                .uri("http://localhost:8399/subnpay/userPaymentMethods/"+id)
                .retrieve()
                .bodyToMono(UserPaymentMethod.class)
                .block();
        System.out.println(userPaymentMethod);
    }

    @Test
    void updatePaymentMethod() {
        long idL = 1;
        String id = String.valueOf(idL);
        UserPaymentMethod userPaymentMethod = new UserPaymentMethod();
        UserPaymentMethod userPaymentMethodResponse = webClientBuilder.build()
                .put()
                .uri("http://localhost:8399/subnpay/userPaymentMethods/"+id)
                .bodyValue(userPaymentMethod)
                .retrieve()
                .bodyToMono(UserPaymentMethod.class)
                .block();
        System.out.println(userPaymentMethodResponse);
    }

    @Test
    void subscriptions() {
        List<Subscription> subscriptions = webClientBuilder.build()
                .get()
                .uri("http://localhost:8399/subnpay/subscriptions")
                .retrieve()
                .bodyToFlux(Subscription.class)
                .collectList()
                .block();
        System.out.println(subscriptions);
    }

    @Test
    void newSubscription() {
        Subscription subscription = new Subscription();
        User user = User.builder().name("Felipe").build();
        subscription.setUser(user);
        subscription.setPlanId(2L);
        Subscription subscriptionResponse = webClientBuilder.build()
                .post()
                .uri("http://localhost:8399/subnpay/subscriptions")
                .bodyValue(subscription)
                .retrieve()
                .bodyToMono(Subscription.class)
                .block();
        System.out.println(subscriptionResponse);
    }


    @Test
    void subscriptionsById() {
        long idL = 1;
        String id = String.valueOf(idL);
        Subscription subscription = webClientBuilder.build()
                .get()
                .uri("http://localhost:8399/subnpay/subscriptions/"+id)
                .retrieve()
                .bodyToMono(Subscription.class)
                .block();
        System.out.println(subscription);
    }

    @Test
    void updateSubscription() {
        long idL = 1;
        String id = String.valueOf(idL);
        Subscription subscription = new Subscription();
        Subscription subscriptionResponse = webClientBuilder.build()
                .put()
                .uri("http://localhost:8399/subnpay/subscriptions/"+id)
                .bodyValue(subscription)
                .retrieve()
                .bodyToMono(Subscription.class)
                .block();
        System.out.println(subscriptionResponse);
    }
}
