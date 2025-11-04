package com.mateus.trip_planner_api.repository;

import com.mateus.trip_planner_api.dto.ActivityTripSumDTO;
import com.mateus.trip_planner_api.models.ActivityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ActivityRepository extends JpaRepository<ActivityModel, UUID> {

    /*

     this.id = id;
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.cost = cost;
        this.trip_id = trip_id;
     */


    @Query("""
       SELECT new com.mateus.trip_planner_api.dto.ActivityTripSumDTO(
           a.id, 
           a.title,
           a.description, 
           a.dateTime,
           a.cost,
           t.id
       )
       FROM TripModel t 
       JOIN t.activities a 
       WHERE t.id = :tripId
       """)
    List<ActivityTripSumDTO> findByTripId(@Param("tripId") UUID tripId);
}
