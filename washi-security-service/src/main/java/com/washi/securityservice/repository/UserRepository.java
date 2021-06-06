package com.washi.securityservice.repository;

import com.washi.securityservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findById(String id);
    //public List<User> findByName(String name);

    //?
    public User findByNumberUser(String numberUser);

    //Just to test microservices communication: find users by district
    public List<User> findByDistrictId(Long districtId);
}
