/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.restservice.controller;

import com.api.restservice.model.City;
import com.api.restservice.model.EssentialTrip;
import com.api.restservice.model.Reservation;
import com.api.restservice.repository.CityRepository;
import com.api.restservice.repository.EssentialTripRepository;
import com.api.restservice.repository.ReservationRepository;
import java.sql.Timestamp;
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
    @Autowired
    ReservationRepository reservationRepository;
    
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
    public List<EssentialTrip> getTrips(@RequestParam("depart") String dep,@RequestParam("arrival") String arr,@RequestParam("date")String dated,@RequestParam("number") String number) throws ParseException{
        String date= dated;
        Date begin = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date+" 00:00:00");
        Date end = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date+" 23:59:59");
        Long depd = new Long(dep);
        Long arrd = new Long(arr);
        int n = Integer.parseInt(number);
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
        List<EssentialTrip> result = essentialTripRepository.find(depd,arrd,begin,end,n);
        return result;
    }
    @GetMapping("/test")
        public List<EssentialTrip> getTrips(){
            return essentialTripRepository.findAll();
        }
    
}