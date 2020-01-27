/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.restservice.controller;

import com.api.restservice.model.Classe;
import com.api.restservice.model.Vehicule;
import com.api.restservice.playload.ApiResponse;
import com.api.restservice.repository.ClasseRepository;
import com.api.restservice.repository.VehiculeRepository;
import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
@RequestMapping("api/backoffice")
public class BackOfficeController {
    @Autowired
    ClasseRepository classeRepository;
    @Autowired
    VehiculeRepository vehiculeRepository;
    
    @Secured("ROLE_ADMIN")
    @GetMapping("/vehicule")
    public List<Vehicule> getUsers() {
        return vehiculeRepository.findAll();
    } 
    @Secured("ROLE_ADMIN")
    @GetMapping("/classes")
    public List<Classe> getClasses() {
        return classeRepository.findAll();
    } 
    @Secured("ROLE_ADMIN")
    @PostMapping("/vehicule/save")
    public ResponseEntity<?> newCar(@Valid @RequestBody Vehicule vehicule) {
        if(vehiculeRepository.existsByImmatriculation(vehicule.getImmatriculation())) {
            return new ResponseEntity(new ApiResponse(400,false, "number taken!"),
                    HttpStatus.BAD_REQUEST);
        }
        Vehicule result = vehiculeRepository.save(vehicule);
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/backoffice/vehicules/{id}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(200,true, "car registered successfully"));
    } 
}
