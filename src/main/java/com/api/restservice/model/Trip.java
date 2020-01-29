/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.restservice.model;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author mariano
 */
@Entity
@Table(name="trip")
public class Trip {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   
    private Timestamp departure_date;
    @NotNull
    private Long id_vehicle ;
    @NotNull
    private Long id_destination ;

    public Timestamp getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(Timestamp departure_date) {
        this.departure_date = departure_date;
    }

    public Long getId_vehicle() {
        return id_vehicle;
    }

    public void setId_vehicle(Long id_vehicle) {
        this.id_vehicle = id_vehicle;
    }

    public Long getId_destination() {
        return id_destination;
    }

    public void setId_destination(Long id_destination) {
        this.id_destination = id_destination;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
