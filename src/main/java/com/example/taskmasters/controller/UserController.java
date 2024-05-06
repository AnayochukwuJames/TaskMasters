package com.example.taskmasters.controller;

import com.example.taskmasters.dto.LoginRequest;
import com.example.taskmasters.dto.LoginResponse;
import com.example.taskmasters.entities.Users;
import com.example.taskmasters.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("{id}")
    public ResponseEntity<Users> getUserById(@PathVariable("id") Long id){
        return userService.getUserById(id);
    }
    @GetMapping("all")
    public ResponseEntity<List<Users>> getAllUsers(){
        return userService.getAllUser();
    }
    @GetMapping("username")
    public ResponseEntity<Users> getUserByUsername(@RequestParam String username){
        return userService.getUserByUsername(username);
    }
    @GetMapping("phoneNumber")
    public ResponseEntity<Users> getUserByPhoneNumber(@RequestParam String phoneNumber){
        return userService.getUserByPhoneNumber(phoneNumber);
    }
    @PutMapping("update")
    public ResponseEntity<Users> updateUser(@PathVariable Long id, @RequestBody @Valid Users user){
        return userService.updateUser(id, user);
    }
}
