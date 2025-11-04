package com.mateus.trip_planner_api.repository;

import com.mateus.trip_planner_api.dto.TripDestinationSumDTO;
import com.mateus.trip_planner_api.dto.UserTripSumDTO;
import com.mateus.trip_planner_api.models.TripModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TripRepository extends JpaRepository<TripModel, UUID> {

    @Query("""
       SELECT new com.mateus.trip_planner_api.dto.UserTripSumDTO(
           t.id, 
           u.name, 
           u.email
       )
       FROM TripModel t 
       JOIN t.users u 
       WHERE t.id = :tripId
       """)
    List<UserTripSumDTO> findUsersByTripId(@Param("tripId") UUID tripId);

    @Query("""
       SELECT new com.mateus.trip_planner_api.dto.TripDestinationSumDTO(
           d.id, 
           d.city, 
           d.country,
           d.description
       )
       FROM TripModel t 
       JOIN t.destinations d 
       WHERE t.id = :tripId
       """)
    List<TripDestinationSumDTO> findDestinationsByTripId(@Param("tripId") UUID tripId);


}

// SELECT u.name from trip_user tu inner join users on tu.user_id = u.id inner join trip t on t.id = tu.trip_id
