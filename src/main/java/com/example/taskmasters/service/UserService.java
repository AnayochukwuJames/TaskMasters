package com.example.taskmasters.service;

import com.example.taskmasters.dto.LoginRequest;
import com.example.taskmasters.dto.LoginResponse;
import com.example.taskmasters.entities.Users;
import com.example.taskmasters.repository.UserRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    private final MessageServices messageServices;

    public ResponseEntity<Users> signUp(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.CREATED);
    }

    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) throws MessagingException {
        Authentication auth = authenticationManager.authenticate(new PreAuthenticatedAuthenticationToken(loginRequest
                .getUsername(), loginRequest.getPassword()));
        if (auth != null) {

            Users user = userRepository.findByUsername(loginRequest.getUsername());
            String token = jwtService.createToken(user);
            messageServices.loginNotification(user.getUsername(), "Dear, \n" + user.getFirstName()+
                    "You have successful login into TaskMaster Services, A platform you can contact a professionals" +
                    " for all your house hold works. Please proceed and place order for the services you may need" +
                    "Note that that you can pay to the service provider after your been satisfied with the service through this platform\n"
                  +  "In case you come across any problem kindly contact us with any of the following numbers: 07066929216, 08030"+
                    "Thank you for chosen Task Master Service Provider");


            return new ResponseEntity<>(LoginResponse.builder()
                    .users(user)
                    .token(token)
                    .build(), HttpStatus.OK);
        }
        return null;
    }
    public ResponseEntity<Users> updateUser(Long id, Users user){
        Users newUser = userRepository.findById(id).get();
        newUser.setFirstName(newUser.getFirstName());
        newUser.setLastName(newUser.getLastName());
        newUser.setMiddleName(newUser.getMiddleName());
        newUser.setPhoneNumber(newUser.getPhoneNumber());
        return new ResponseEntity<>(userRepository.save(user), HttpStatus.ACCEPTED);

    }
    public ResponseEntity<Users> getUserById(Long id){
        Users user = userRepository.findById(id).get();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    public ResponseEntity<List<Users>> getAllUser(){
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);

    }
    public ResponseEntity<Users> getUserByUsername(String username){
        return new ResponseEntity<>(userRepository.findByUsername(username), HttpStatus.OK);
    }

    public ResponseEntity<Users> getUserByPhoneNumber(String phoneNumber){
        return new ResponseEntity<>(userRepository.findByPhoneNumber(phoneNumber), HttpStatus.OK);
    }
}
