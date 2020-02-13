/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.restservice.model;

import java.sql.Timestamp;
import javax.persistence.Column;
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
@Table(name="reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(name="id_users")
    Long idUsers ;
    
    @NotNull
    Timestamp date ;
    
    @NotNull
    @Column(name="id_trip")
    Long idTrip ;
    
    @Column(name="id_place")
    @NotNull
    Long idPlace;
    int etat;

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
     
     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUsers() {
        return idUsers;
    }

    public void setIdUsers(Long idUsers) {
        this.idUsers = idUsers;
    }

    

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Long getIdTrip() {
        return idTrip;
    }

    public void setIdTrip(Long id_trip) {
        this.idTrip = id_trip;
    }

    public Long getIdPlace() {
        return idPlace;
    }

    public void setidPlace(Long id_place) {
        this.idPlace = id_place;
    }
}
