/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.api.restservice.repository;

import com.api.restservice.model.EssentialTrip;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author mariano
 */
public interface EssentialTripRepository extends JpaRepository<EssentialTrip,Long>{
    List<EssentialTrip> findByDepartCityAndArrivalCityAndDepartureDateAndLibreGreaterThanEqual(Long d,Long a,Timestamp date,int number);
    @Query(value="select * from trip_essential where depart = :depart and arrival = :arrival and departure_date between :begin and :end and libre >= :number", nativeQuery = true)
    List<EssentialTrip> find(@Param("depart") Long depart,@Param("arrival") Long arrival,@Param("begin") Date begin , @Param("end") Date end, @Param("number") int number);
  
}
