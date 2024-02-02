package com.example.taskmasters.service;

import com.example.taskmasters.dto.GoogleSignUpRequest;
import com.example.taskmasters.dto.SignUpRequest;
import com.example.taskmasters.dto.SignUpResponse;
import com.example.taskmasters.entities.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;


public interface UserService {
    ResponseEntity<SignUpResponse> signUp(SignUpRequest signUpRequest);


    @Transactional
    Users signUpWithGoogle(GoogleSignUpRequest googleSignUpRequest);
}
