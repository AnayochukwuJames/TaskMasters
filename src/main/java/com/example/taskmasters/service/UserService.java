package com.example.taskmasters.service;

import com.example.taskmasters.entities.Users;
import com.example.taskmasters.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthRepository authRepository;

    public ResponseEntity<Users> updateUser(Long id, Users user){
        Users newUser = authRepository.findById(id).get();
        newUser.setFirstName(newUser.getFirstName());
        newUser.setLastName(newUser.getLastName());
        newUser.setMiddleName(newUser.getMiddleName());
        newUser.setPhoneNumber(newUser.getPhoneNumber());
        return new ResponseEntity<>(authRepository.save(user), HttpStatus.ACCEPTED);

    }
    public ResponseEntity<Users> getUserById(Long id){
        Users user = authRepository.findById(id).get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    public ResponseEntity<List<Users>> getAllUser(){
        return new ResponseEntity<>(authRepository.findAll(), HttpStatus.OK);

    }
    public ResponseEntity<Users> getUserByUsername(String username){
        return new ResponseEntity<>(authRepository.findByUsername(username), HttpStatus.OK);
    }

    public ResponseEntity<Users> getUserByPhoneNumber(String phoneNumber){
        return new ResponseEntity<>(authRepository.findByPhoneNumber(phoneNumber), HttpStatus.OK);
    }
}
