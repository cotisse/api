/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools  Templates
 * and open the template in the editor.
 */
package com.api.restservice.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author mariano
 */
@Entity
@Table(name="trip_essential")
public class EssentialTrip {
 @Id
 private Long id  ;
 
 @Temporal(TemporalType.TIMESTAMP)
 @Column(name="departure_date")
 private Date departureDate;
 
 private Long depart ;
 
 private Long arrival; 
 
 @Column(name="depart_city")
 private String departCity  ;
 
 @Column(name="depart_code")
 private String departCode ;
 
 @Column(name="arrival_city")
 private String arrivalCity; 
 
 @Column(name="arrival_code")
 private String arrivalCode; 
 
 @Column(name="id_classe")
 private Long idClasse ;
 
 private String classe ;
 private double total ;
 private int libre;

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

    public String getDepartCity() {
        return departCity;
    }

    public void setDepartCity(String departCity) {
        this.departCity = departCity;
    }

    public String getDepartCode() {
        return departCode;
    }

    public void setDepartCode(String departCode) {
        this.departCode = departCode;
    }

    public String getArrivalCity() {
        return arrivalCity;
    }

    public void setArrivalCity(String arrivalCity) {
        this.arrivalCity = arrivalCity;
    }

    public String getArrivalCode() {
        return arrivalCode;
    }

    public void setArrivalCode(String arrivalCode) {
        this.arrivalCode = arrivalCode;
    }

    public Long getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(Long idClasse) {
        this.idClasse = idClasse;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getLibre() {
        return libre;
    }

    public void setLibre(int libre) {
        this.libre = libre;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }
 
}
