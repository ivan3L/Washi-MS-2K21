package com.washi.businessservice.client;

import com.washi.businessservice.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserHystrixFallbackFactory implements UserClient {
    @Override
    public ResponseEntity<User> getUser(long id){
        User user = User.builder()
                .name("none")
                .build();
        return ResponseEntity.ok(user);
    }
}
