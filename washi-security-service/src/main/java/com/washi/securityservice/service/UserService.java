package com.washi.securityservice.service;

import com.washi.securityservice.entity.User;

import java.util.List;

public interface UserService {
    public List<User> findAll();

    public User createUser(User user);
    public User updateUser(User user);
    public User deleteUser(User user);

    public User getUser(Long id);

}
