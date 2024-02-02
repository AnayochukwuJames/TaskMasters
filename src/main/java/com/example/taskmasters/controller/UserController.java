package com.example.taskmasters.controller;

import com.example.taskmasters.dto.GoogleSignUpRequest;
import com.example.taskmasters.dto.SignUpRequest;
import com.example.taskmasters.dto.SignUpResponse;
import com.example.taskmasters.entities.Users;
import com.example.taskmasters.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/user/")
public class UserController {

    private final UserService userService;

    @PostMapping("sign-up")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody SignUpRequest signUpRequest){
        return userService.signUp(signUpRequest);
    }

    @PostMapping("/google-sign-up")
    public Users signUpWithGoogle(@RequestBody GoogleSignUpRequest googleSignUpRequest) {
     return userService.signUpWithGoogle(googleSignUpRequest);
    }
}
