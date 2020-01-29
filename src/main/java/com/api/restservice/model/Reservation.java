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
@Table(name="reservation")
public class Reservation {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     @NotNull
    Long id_users ;
     @NotNull
    Timestamp date ;
     @NotNull
    Long id_trip ;
     @NotNull
    Long id_place;
}
