/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.restservice.controller;

import com.api.restservice.model.Brand;
import com.api.restservice.model.Classe;
import com.api.restservice.model.CompleteVehicle;
import com.api.restservice.model.Destination;
import com.api.restservice.model.Place;
import com.api.restservice.model.Trip;
import com.api.restservice.model.Vehicle;
import com.api.restservice.playload.ApiResponse;
import com.api.restservice.repository.BrandRepository;
import com.api.restservice.repository.ClasseRepository;
import com.api.restservice.repository.CompleteVehicleRepository;
import com.api.restservice.repository.DestinationRepository;
import com.api.restservice.repository.PlaceRepository;
import com.api.restservice.repository.TripRepository;
import com.api.restservice.repository.VehiculeRepository;
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
@RequestMapping("api/backoffice")
public class BackOfficeController {
    @Autowired
    ClasseRepository classeRepository;
    @Autowired
    VehiculeRepository vehiculeRepository;
    @Autowired
    CompleteVehicleRepository completeVehicleRepository;
    @Autowired
    PlaceRepository placeRepository;
    @Autowired
    BrandRepository brandRepository;
    @Autowired
    TripRepository tripRepository;
    @Autowired
    DestinationRepository destinationRepository;
    
    
    @Secured("ROLE_ADMIN")
    @GetMapping("/vehicule")
    public List<Vehicle> getVehicles() {
        return vehiculeRepository.findAll();
    } 
    @Secured("ROLE_ADMIN")
    @GetMapping("/vehiculeDetails")
    public List<CompleteVehicle> getVehiclesDetailled() {
        return completeVehicleRepository.findAll();
    } 
    @Secured("ROLE_ADMIN")
    @GetMapping("/classes")
    public List<Classe> getClasses() {
        return classeRepository.findAll();
    } 
    @Secured("ROLE_ADMIN")
    @GetMapping("/brands")
    public List<Brand> getBrands() {
        return brandRepository.findAll();
    } 
    @Secured("ROLE_ADMIN")
    @GetMapping("/trips")
    public List<Trip> getTrips() {
        return tripRepository.findAll();
    } 
    @Secured("ROLE_ADMIN")
    @GetMapping("/vehicule/{class}")
    public List<CompleteVehicle> getVehiculeByClass(@PathVariable(value="class") Long classe) {
        return completeVehicleRepository.findByIdClasse(classe);
    } 
    @Secured("ROLE_ADMIN")
    @GetMapping("/destinations")
    public List<Destination> getDestination() {
        return destinationRepository.findAll();
    } 
    @Secured("ROLE_ADMIN")
    @PostMapping("/vehicule/save")
    @Transactional
    public ResponseEntity<?> newCar(@Valid @RequestBody Vehicle vehicule) {
        if(vehiculeRepository.existsByRegistration(vehicule.getRegistration())) {
            return new ResponseEntity(new ApiResponse(400,false, "number taken!"),
                    HttpStatus.BAD_REQUEST);
        }
        Vehicle result = vehiculeRepository.save(vehicule);
       
        for(int i = 0 ; i < result.getPlace_number() ; i++){
            Place item = new Place(i,result.getId());
            placeRepository.save(item);
        }
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/backoffice/vehicules/{id}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(200,true, "car registered successfully"));
    } 
    @Secured("ROLE_ADMIN")
    @PostMapping("/trip/save")
    @Transactional
    public ResponseEntity<?> newTrip(@Valid @RequestBody Trip trip) {
       
        Trip result = tripRepository.save(trip);
       
       
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/backoffice/trips/{id}")
                .buildAndExpand(result.getId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(200,true, "trip registered successfully"));
    } 
}
