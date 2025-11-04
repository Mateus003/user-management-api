package com.mateus.trip_planner_api.repository;

import com.mateus.trip_planner_api.models.DestinationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DestinationRepository extends JpaRepository<DestinationModel, UUID> {

    @Query("SELECT d FROM DestinationModel d where d.city = :city")
    List<DestinationModel> findByCity(@Param("city") String city);

    @Query("SELECT d From DestinationModel d where d.country = :contry")
    List<DestinationModel> findByCountry(@Param("contry") String contry);

}
