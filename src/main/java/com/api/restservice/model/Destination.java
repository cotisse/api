/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools ; Templates
 * and open the template in the editor.
 */
package com.api.restservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author mariano
 */
@Entity
@Table(name="complete_destination")
public class Destination {
    @Id
    Long id ;
    Long depart; 
    Long arrival; 
    double price; ; 
    String depart_city;
    String depart_code; 
    String arrival_city; 
    String arrival_code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDepart() {
        return depart;
    }

    public void setDepart(Long depart) {
        this.depart = depart;
    }

    public Long getArrival() {
        return arrival;
    }

    public void setArrival(Long arrival) {
        this.arrival = arrival;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDepart_city() {
        return depart_city;
    }

    public void setDepart_city(String depart_city) {
        this.depart_city = depart_city;
    }

    public String getDepart_code() {
        return depart_code;
    }

    public void setDepart_code(String depart_code) {
        this.depart_code = depart_code;
    }

    public String getArrival_city() {
        return arrival_city;
    }

    public void setArrival_city(String arrival_city) {
        this.arrival_city = arrival_city;
    }

    public String getArrival_code() {
        return arrival_code;
    }

    public void setArrival_code(String arrival_code) {
        this.arrival_code = arrival_code;
    }
    
}
