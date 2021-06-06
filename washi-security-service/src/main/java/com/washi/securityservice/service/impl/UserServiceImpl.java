package com.washi.securityservice.service.impl;

import com.washi.securityservice.client.DistrictClient;
import com.washi.securityservice.entity.User;
import com.washi.securityservice.model.District;
import com.washi.securityservice.repository.UserRepository;
import com.washi.securityservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    DistrictClient districtClient;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        User userDB = userRepository.findByNumberUser(user.getNumberUser());
        if(userDB != null){
            return userDB;
        }
        user.setState("CREATED");
        userDB = userRepository.save(user);

        return userDB;
    }

    @Override
    public User updateUser(User user) {
        User userDB = getUser(user.getId());
        if(userDB == null){
            return null;
        }
        userDB.setDistrictId(user.getDistrictId());
        userDB.setNumberUser(user.getNumberUser());
        userDB.setEmail(user.getEmail());
        userDB.setPassword(user.getPassword());

        return userRepository.save(userDB);
    }

    @Override
    public User deleteUser(User user) {
        User userDB = getUser(user.getId());
        if(userDB == null){
            return null;
        }
        userDB.setState("DELETED");
        return userRepository.save(userDB);
    }

    @Override
    public User getUser(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null){
            District district = districtClient.getDistrict(user.getDistrictId()).getBody();
            user.setDistrict(district);
        }
        return user;
    }
}
