package com.example.taskmasters.service;

import com.example.taskmasters.dto.*;
import com.example.taskmasters.entities.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;


public interface UserService {
    ResponseEntity<SignUpResponse> signUp(SignUpRequest signUpRequest);


    @Transactional
    Users signUpWithGoogle(GoogleSignUpRequest googleSignUpRequest);

    UserDetailsService userDetailsService();

    UserDetails loadUserByUsername(String userEmail);
    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);

//    JwtAuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
