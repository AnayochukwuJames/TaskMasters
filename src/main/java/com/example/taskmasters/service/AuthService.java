package com.example.taskmasters.service;

import com.example.taskmasters.dto.LoginRequest;
import com.example.taskmasters.dto.LoginResponse;
import com.example.taskmasters.entities.Users;
import com.example.taskmasters.repository.AuthRepository;
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
public class AuthService {

    private final AuthRepository authRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JWTService jwtService;

    private final MessageServices messageServices;

    public ResponseEntity<Users> signUp(Users user) throws MessagingException {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        messageServices.registrationNotification(user.getUsername(), "Dear" + user.getFirstName());
        return new ResponseEntity<>(authRepository.save(user), HttpStatus.CREATED);
    }

    public ResponseEntity<LoginResponse> login(LoginRequest loginRequest) throws MessagingException {
        Authentication auth = authenticationManager.authenticate(new PreAuthenticatedAuthenticationToken(loginRequest
                .getUsername(), loginRequest.getPassword()));
        if (auth != null) {

            Users user = authRepository.findByUsername(loginRequest.getUsername());
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

}
