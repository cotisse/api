/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools 
 Templates
 * and open the template in the editor.
 */
package com.api.restservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 *
 * @author mariano
 */
@Entity
@Table(name="places_state")
//@Cacheable(false)
@IdClass(CompositeKey.class)
public class PlaceState {
 @Id
 public Long id_place ;
 @Id
 @Column(name="id_trip")
 public Long idTrip ;
 public Long id_vehicle ;
 public int number ;
 public int int_state ;
 public String label_state ;
 public int etat ;

    

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getInt_state() {
        return int_state;
    }

    public void setInt_state(int int_state) {
        this.int_state = int_state;
    }

    public String getLabel_state() {
        return label_state;
    }

    public void setLabel_state(String label_state) {
        this.label_state = label_state;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public Long getId_place() {
        return id_place;
    }

    public void setId_place(Long id_place) {
        this.id_place = id_place;
    }

    public Long getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(Long idTrip) {
        this.idTrip = idTrip;
    }

    public Long getId_vehicle() {
        return id_vehicle;
    }

    public void setId_vehicle(Long id_vehicle) {
        this.id_vehicle = id_vehicle;
    }

   
 
 
}
