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
import javax.persistence.Id;
import javax.persistence.Table;


/**
 *
 * @author mariano
 */
@Entity
@Table(name="complete_reservation")
public class ReservationComplet {
 @Id
 Long id;
 @Column(name="id_users")
 Long idUsers;
 Timestamp date;
 Long id_trip;
 Long id_place ;
 Timestamp departure_date    ;
 String depart_city  ;
 String depart_code ;
 String arrival_city ;
 String arrival_code ;
 String classe ;
 double total ;
 int libre ;
 int number;  
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

    public Long getId_trip() {
        return id_trip;
    }

    public void setId_trip(Long id_trip) {
        this.id_trip = id_trip;
    }

    public Long getId_place() {
        return id_place;
    }

    public void setId_place(Long id_place) {
        this.id_place = id_place;
    }

    public Timestamp getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(Timestamp departure_date) {
        this.departure_date = departure_date;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
 
 
}
