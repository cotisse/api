/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.restservice.controller;

import com.api.restservice.model.City;
import com.api.restservice.model.EssentialTrip;
import com.api.restservice.repository.CityRepository;
import com.api.restservice.repository.EssentialTripRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author mariano
 */
@CrossOrigin
@RestController
@RequestMapping("api/public")
public class PublicController {
    @Autowired
    CityRepository cityRepository;
    @Autowired
    EssentialTripRepository essentialTripRepository;
    
    @GetMapping("/cities")
    public List<City> cities() {
        return cityRepository.findAll();
    } 
//    @GetMapping("/trips")
//    public List<EssentialTrip> getTrips(@RequestBody TripRequest tr) throws ParseException{
//        String date= tr.getDate();
//        Date begin = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date+" 00:00:00");
//        Date end = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date+" 23:59:59");
//        Long dep = tr.getDepart();
//        Long arr = tr.getArrival();
//        List<EssentialTrip> result = essentialTripRepository.find(dep,arr,begin,end,tr.getNumber());
//        return result;
//    }
     @GetMapping("/trips")
//    public List<EssentialTrip> getTrips(@PathVariable(name = "depart") String dep,@PathVariable(name ="arrival") String arr,@PathVariable(name = "date") String dated,@PathVariable (name="number")String number) throws ParseException{
    public List<EssentialTrip> getTrips(@RequestParam("depart") String dep,@RequestParam("arrival") String arr,@RequestParam("date")String dated,@RequestParam("number") String number) throws ParseException{
//        return id;
        String date= dated;
        Date begin = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date+" 00:00:00");
        Date end = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date+" 23:59:59");
        Long depd = new Long(dep);
        Long arrd = new Long(arr);
        int n = Integer.parseInt(number);
        List<EssentialTrip> result = essentialTripRepository.find(depd,arrd,begin,end,n);
//        for(EssentialTrip item : result){
//            
//        }
        return result;
    }
    @GetMapping("/test")
        public List<EssentialTrip> getTrips(){
            return essentialTripRepository.findAll();
        }
    
}