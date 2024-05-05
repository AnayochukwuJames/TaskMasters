package com.example.taskmasters.controller;

import com.example.taskmasters.dto.LoginRequest;
import com.example.taskmasters.dto.LoginResponse;
import com.example.taskmasters.entities.Users;
import com.example.taskmasters.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
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
    public ResponseEntity<Users> signUp(@RequestBody Users user){
        return userService.signUp(user);
    }
    @PostMapping("login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest loginRequest) throws MessagingException {
        return userService.login(loginRequest);
    }

//    @PostMapping("/google-sign-up")
//    public Users signUpWithGoogle(@RequestBody GoogleSignUpRequest googleSignUpRequest) {
//     return userService.signUpWithGoogle(googleSignUpRequest);
//    }

}
