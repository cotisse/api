/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.restservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 *
 * @author mariano
 */
@Entity
@Table(name="place")
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    int number;
    
    @NotNull
    int int_state;
    
    @NotBlank
    String label_state;
    
    @NotNull
    Long id_vehicle;

    public Place(int number,Long id_vh) {
        this.number = number;
        this.int_state = 1;
        this.label_state ="pass";
        this.id_vehicle = id_vh;
    }
    
}
