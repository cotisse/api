/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.restservice.repository;

import com.api.restservice.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author mariano
 */
public interface UserRepository extends JpaRepository<User, Long>{
    User findByPhone(String Phone);
    Optional<User> findByPhoneOrEmail(String Phone, String email);
    List<User> findByIdIn(List<Long> userIds);
    Optional<User> findByname(String name);
    Boolean existsByPhone(String username);
    Boolean existsByEmail(String email);
     
}
