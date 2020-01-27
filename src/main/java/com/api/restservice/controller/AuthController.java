/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.restservice.controller;


import com.api.restservice.exception.AppException;
import com.api.restservice.model.Role;
import com.api.restservice.model.RoleName;
import com.api.restservice.model.User;
import com.api.restservice.playload.ApiResponse;
import com.api.restservice.playload.JwtAuthenticationResponse;
import com.api.restservice.playload.LoginRequest;
import com.api.restservice.playload.SignUpRequest;
import com.api.restservice.repository.RoleRepository;
import com.api.restservice.repository.UserRepository;
import com.api.restservice.security.JwtTokenProvider;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
/**
 *
 * @author mariano
 */
@CrossOrigin
@RestController
@RequestMapping("api/auth")
public class AuthController {
     @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) throws Exception {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getPhoneOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        //doublons verifications
        if(userRepository.existsByPhone(signUpRequest.getPhone())) {
            return new ResponseEntity(new ApiResponse(400,false, "Phone is already connected to another account!"),
                    HttpStatus.BAD_REQUEST);
        }
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new ResponseEntity(new ApiResponse(400,false, "Email Address already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
        
        // Creating user's account
        User user = new User(signUpRequest.getName(), signUpRequest.getPhone(),
                signUpRequest.getEmail(), signUpRequest.getPassword());
//      HASH PASSWORD
        user.setPassword(passwordEncoder.encode(user.getPassword()));

//      DEFAULT ROLE
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

//      saving on database
        User result = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/users/{phone}")
                .buildAndExpand(result.getPhone()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(200,true, "User registered successfully"));
    }

    @GetMapping("/test")
    public List<User> getUsers() {
        return userRepository.findAll();
    }  
}
