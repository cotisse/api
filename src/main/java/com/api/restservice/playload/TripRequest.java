/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.restservice.playload;

/**
 *
 * @author mariano
 */
public class TripRequest {
    private Long depart;
    private Long arrival;
    
    private String date;
    private int number;

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
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
