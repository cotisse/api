/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.restservice.repository;

import com.api.restservice.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author mariano
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Boolean existsByIdTripAndIdPlace(Long idTrip, Long idPlace);
    Reservation findByIdUsers( Long idUsers);
}
