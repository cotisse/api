/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.restservice.security;

import com.api.restservice.model.User;
import com.api.restservice.repository.UserRepository;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author mariano
 */
@Service

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
//   
    public UserDetails loadUserByUsername(String phoneOrEmail)
            throws UsernameNotFoundException {
        // Let people login with either phone or email
        User user = userRepository.findByPhoneOrEmail(phoneOrEmail, phoneOrEmail).orElseThrow(
                () -> new UsernameNotFoundException("User not found with phone or email : " + phoneOrEmail)
        );
        return UserPrincipal.create(user);
    }
     // This method is used by JWTAuthenticationFilter
    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new UsernameNotFoundException("User not found with id : " + id)
        );
        return UserPrincipal.create(user);
    }
}