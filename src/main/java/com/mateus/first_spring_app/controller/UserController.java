package com.mateus.first_spring_app.controller;

import com.mateus.first_spring_app.dto.UserDTO;
import com.mateus.first_spring_app.model.UserModel;
import com.mateus.first_spring_app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<UserModel> addUser(@RequestBody  @Valid UserDTO userDTO) {
            UserModel savedUser = userService.save(userDTO);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(savedUser);
    }


    @GetMapping("/search/email")
    public ResponseEntity<UserModel> getByEmail(@RequestParam String value) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findByEmail(value));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable String id) {
        UUID uuid = UUID.fromString(id);
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(uuid));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable String id) {
        userService.deleteById(UUID.fromString(id));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable String id,@RequestBody @Valid UserDTO userDTO) {
        userService.update(UUID.fromString(id), userDTO);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<UserModel>> getByName(@RequestParam String name) {
        return  ResponseEntity.status(HttpStatus.OK)
                .body(userService.findAllByName(name));
    }


}