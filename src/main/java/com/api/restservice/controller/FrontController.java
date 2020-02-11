/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.restservice.controller;

import com.api.restservice.model.Reservation;
import com.api.restservice.playload.ApiResponse;
import com.api.restservice.playload.ReservationRequest;
import com.api.restservice.repository.CityRepository;
import com.api.restservice.repository.DestinationRepository;
import com.api.restservice.repository.ReservationRepository;
import com.api.restservice.security.CurrentUser;
import com.api.restservice.security.JwtTokenProvider;
import com.api.restservice.security.UserPrincipal;
import java.net.URI;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
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

/**
 *
 * @author mariano
 */
@CrossOrigin
@RestController
@RequestMapping("api/web")
public class FrontController {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    JwtTokenProvider provider;
    @Autowired
    DestinationRepository destinationRepository;
    
//    @Secured("ROLE_USER")
//    @PostMapping("/reservation/save")
//    @Transactional
//    public ResponseEntity<?> reserver(@Valid @RequestBody Reservation res) {
//         if(reservationRepository.existsByIdTripAndIdPlace(res.getIdTrip(),res.getIdPlace())) {
//            return new ResponseEntity(new ApiResponse(400,false, "place taken!"),
//                    HttpStatus.BAD_REQUEST);
//        }
//        Reservation result = reservationRepository.save(res);
//       
//        
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentContextPath().path("/api/front/reservations/{id}")
//                .buildAndExpand(result.getId()).toUri();
//
//        return ResponseEntity.created(location).body(new ApiResponse(200,true, "trip registered successfully"));
//    } 
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
    @Secured("ROLE_USER")
    @PostMapping("/reservation/save")
    public ResponseEntity<?> myReservation(@CurrentUser UserPrincipal currentUser, @RequestBody ReservationRequest[] req){
        Long id = currentUser.getId();
        List<Reservation> result = new ArrayList();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        for(ReservationRequest item : req) {
            Long trip = item.getIdTrip();
            Long place = item.getId_place();
            if(reservationRepository.existsByIdTripAndIdPlace(trip,place)) {
                return new ResponseEntity(new ApiResponse(400,false, "place taken!"),
                        HttpStatus.BAD_REQUEST);
            }
            Reservation r = new Reservation();
            r.setDate(timestamp);
            r.setIdUsers(id);
            r.setIdTrip(trip);
            r.setidPlace(place);
            result.add(r);
        }
        reservationRepository.saveAll(result);
//        URI location = "/reservation";
        URI location = URI.create("/reservations");
        return ResponseEntity.created(location).body(new ApiResponse(200,true, "trip registered successfully"));

    }
}
