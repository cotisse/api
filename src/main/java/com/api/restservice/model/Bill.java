/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools ;
 Templates
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

/**
 *
 * @author mariano
 */
@Entity
@Table(name="bill")
public class Bill {
 @Id
         @GeneratedValue(strategy = GenerationType.IDENTITY)
 Long id ;
 @Column(name="id_reservation")
 Long idReservation ;
 double total ;
 Timestamp date;

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
 

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

 
}
