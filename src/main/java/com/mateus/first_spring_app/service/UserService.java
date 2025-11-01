package com.mateus.first_spring_app.service;

import com.mateus.first_spring_app.dto.UserDTO;
import com.mateus.first_spring_app.model.UserModel;
import com.mateus.first_spring_app.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public  UserService(UserRepository userRepository,  PasswordEncoder passwordEncoder) {
        this.userRepository =userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserModel save(UserDTO userDTO) {
        UserModel userModel = new UserModel();
        userModel.setName(userDTO.getName());
        userModel.setEmail(userDTO.getEmail());
        userModel.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        return userRepository.save(userModel);

    }

    public List<UserModel> findAll() {
        return userRepository.findAll();
    }

    public UserModel findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserModel findById(UUID id) {
        if (id != null) {
            return userRepository.findById(id).orElse(null);
        }

        return null;
    }

    @Transactional
    public void  deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public UserModel update(UUID id, UserDTO userDTO) {
        UserModel userModel = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        userModel.setName(userDTO.getName());
        userModel.setEmail(userDTO.getEmail());
        userModel.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        return userRepository.save(userModel);
    }


public List<UserModel> findAllByName(String name){
        return  userRepository.findByName(name);
}



}
