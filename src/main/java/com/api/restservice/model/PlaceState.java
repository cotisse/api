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
import javax.persistence.Table;

/**
 *
 * @author mariano
 */
@Entity
@Table(name="places_state")
public class PlaceState {
 @Id
 private String id_place ;
 @Column(name="id_trip")
 private String idTrip ;
 private String id_vehicle ;
 private int number ;
 private int int_state ;
 private String label_state ;
 private int etat ;

    public String getId_place() {
        return id_place;
    }

    public void setId_place(String id_place) {
        this.id_place = id_place;
    }
    public String getId_vehicle() {
        return id_vehicle;
    }

    public void setId_vehicle(String id_vehicle) {
        this.id_vehicle = id_vehicle;
    }

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

    public String getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(String idTrip) {
        this.idTrip = idTrip;
    }
 
 
}
