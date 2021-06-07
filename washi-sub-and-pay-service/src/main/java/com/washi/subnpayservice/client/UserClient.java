package com.washi.subnpayservice.client;

import com.washi.subnpayservice.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "security-service", fallback = UserHystrixFallbackFactory.class)
//@RequestMapping(value = "/security/users"
public interface UserClient {
    @GetMapping(value = "/security/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable("id") long id);
}
