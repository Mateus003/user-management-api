package com.mateus.first_spring_app.repository;

import com.mateus.first_spring_app.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID>{

    List<UserModel> findByName(String name);

    UserModel findByEmail(String email);
}
