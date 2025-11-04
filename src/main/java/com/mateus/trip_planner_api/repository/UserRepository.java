package com.mateus.trip_planner_api.repository;

import com.mateus.trip_planner_api.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID>{

    List<UserModel> findByName(String name);

    UserModel findByEmail(String email);
}
