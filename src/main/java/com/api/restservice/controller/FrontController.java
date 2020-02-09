/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.restservice.controller;

import com.api.restservice.model.Reservation;
import com.api.restservice.playload.ApiResponse;
import com.api.restservice.repository.CityRepository;
import com.api.restservice.repository.ReservationRepository;
import java.net.URI;
import java.util.List;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("api/Web")
public class FrontController {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    CityRepository cityRepository;
    
    @Secured("ROLE_USER")
    @PostMapping("/reservation/save")
    @Transactional
    public ResponseEntity<?> reserver(@Valid @RequestBody Reservation res) {
         if(reservationRepository.existsByIdTripAndIdPlace(res.getIdTrip(),res.getIdPlace())) {
            return new ResponseEntity(new ApiResponse(400,false, "place taken!"),
                    HttpStatus.BAD_REQUEST);
        }
        Reservation result = reservationRepository.save(res);
       
        
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/front/reservations/{id}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(200,true, "trip registered successfully"));
    } 
    @Secured("ROLE_USER")
    @PostMapping("/bill/save")
    @Transactional
    public void pay() {
//        return ResponseEntity.created(location).body(new ApiResponse(200,true, " registered successfully"));
    } 
    @Secured("ROLE_USER")
    @GetMapping("/reservation/{Mid}")
    public List<Reservation> myReservation(@PathVariable(value="Mid") Long Mid) {
        return reservationRepository.findByIdUsers(Mid);
    }
}
