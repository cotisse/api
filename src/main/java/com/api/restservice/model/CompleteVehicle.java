/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
@Table(name="complete_vehicle")
public class CompleteVehicle {
    @Id
    private Long id ;
    
    private Long id_brand;
    @Column(name="id_classe")
    private Long idClasse;
    private int place_number;
    private String registration ;
    private String description;
    private String brand;
    private String classe;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId_brand() {
        return id_brand;
    }

    public void setId_brand(Long id_brand) {
        this.id_brand = id_brand;
    }

    public Long getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(Long idClasse) {
        this.idClasse = idClasse;
    }

    public int getPlace_number() {
        return place_number;
    }

    public void setPlace_number(int place_number) {
        this.place_number = place_number;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }
}
