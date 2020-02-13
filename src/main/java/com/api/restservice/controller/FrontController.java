/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.restservice.controller;

import com.api.restservice.model.Bill;
import com.api.restservice.model.Reservation;
import com.api.restservice.model.ReservationComplet;
import com.api.restservice.playload.ApiResponse;
import com.api.restservice.playload.BillRequest;
import com.api.restservice.playload.ReservationRequest;
import com.api.restservice.repository.BillRepository;
import com.api.restservice.repository.CityRepository;
import com.api.restservice.repository.DestinationRepository;
import com.api.restservice.repository.ReservationCompletRepository;
import com.api.restservice.repository.ReservationRepository;
import com.api.restservice.security.CurrentUser;
import com.api.restservice.security.JwtTokenProvider;
import com.api.restservice.security.UserPrincipal;
import java.net.URI;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    @Autowired
    ReservationCompletRepository reservationCompletRepository;
    @Autowired
    BillRepository billRepository;
      
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
            r.setEtat(1);
            result.add(r);
        }
        reservationRepository.saveAll(result);
        URI location = URI.create("/reservations");
        return ResponseEntity.created(location).body(new ApiResponse(200,true, "trip registered successfully"));

    }
    @Secured("ROLE_USER")
    @PostMapping("/bill/save")
    public ResponseEntity<?> saveBill(@RequestBody BillRequest[] req){
        List<Bill> result = new ArrayList();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        for(BillRequest item :req){
            Long idres = item.getId();
            double total = item.getTotal();
            if(billRepository.existsByIdReservation(idres)){
                return new ResponseEntity(new ApiResponse(400,false, "already paid!"),
                        HttpStatus.BAD_REQUEST);
            }
            Bill b = new Bill();
            b.setIdReservation(idres);
            b.setTotal(total);
            b.setDate(timestamp);
            result.add(b);
        }
        billRepository.saveAll(result);
        for(Bill item : result){
            Optional<Reservation> res = reservationRepository.findById(item.getIdReservation());
            Reservation selected = res.get();
            selected.setEtat(10);
            reservationRepository.save(selected);
        }
        URI location = URI.create("/bills");
        return ResponseEntity.created(location).body(new ApiResponse(200,true, "bill paid successfully"));
    }
    @Secured("ROLE_USER")
    @GetMapping("/reservations")
    public List<ReservationComplet> myReservation(@CurrentUser UserPrincipal currentUser){
        Long id = currentUser.getId();
        //but effacer les reservations qui ne sont pas payés dans les 5 min
        //get reservations where etat <10 (non payés)
        List<Reservation> notPayed = reservationRepository.findByEtatBefore(10);
        //forEach reservation
        Timestamp current = new Timestamp(System.currentTimeMillis());
        for(Reservation item : notPayed){
        //if currentDate - reservation.date > 5 min => delete reservation
            long milliseconds  = current.getTime() - item.getDate().getTime();
            int seconds = (int)milliseconds/1000; 
            int minutes  = (seconds % 3600) /60;
            if(minutes > 5){
                reservationRepository.delete(item);
            }
        }   
        List<ReservationComplet> result = reservationCompletRepository.findAllByIdUsers(id);
        return result;
    }
}
