package com.example.taskmasters.service.serviceImp;

import com.example.taskmasters.dto.EmailRequest;
import com.example.taskmasters.dto.GoogleSignUpRequest;
import com.example.taskmasters.dto.SignUpRequest;
import com.example.taskmasters.dto.SignUpResponse;
import com.example.taskmasters.entities.MailMessages;
import com.example.taskmasters.entities.Users;
import com.example.taskmasters.repository.UserRepository;
import com.example.taskmasters.service.UserService;
import com.example.taskmasters.validation.PasswordValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public ResponseEntity<SignUpResponse> signUp(SignUpRequest signUpRequest) {
        try {
            if (userRepository.findByUsernameIgnoreCase(signUpRequest.getUsername()).isPresent()) {
                throw new BadRequestException("User already exists");
            }

            if (!PasswordValidator.isValid(signUpRequest.getPassword())) {
                throw new BadRequestException("Invalid password format");
            }

            Users users = mapSignUpRequestToUser(signUpRequest);
            users.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
            Users savedUser = userRepository.save(users);

            String message = MailMessages.verifyEmail(savedUser.getFirstName());
            EmailRequest emailRequest = EmailRequest.builder()
                    .recipient(savedUser.getUsername())
                    .subject("VERIFICATION EMAIL")
                    .messageBody(message)
                    .build();

            SignUpResponse signUpResponse = SignUpResponse.builder()
                    .message("User sign-up successful. Kindly login with your email and password.")
                    .firstName(savedUser.getFirstName())
                    .email(savedUser.getUsername())
                    .httpStatus(HttpStatus.CREATED)
                    .build();

            return ResponseEntity.ok(signUpResponse);
        } catch (Exception e) {
            throw new RuntimeException("Error during sign-up", e);
        }
    }
    private Users mapSignUpRequestToUser(SignUpRequest signUpRequest) {
        return Users.builder()
                .firstName(signUpRequest.getFirstName())
                .lastName(signUpRequest.getLastName())
                .phoneNumber(signUpRequest.getPhoneNumber())
                .username(signUpRequest.getUsername())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .build();
    }
    @Override
    @Transactional
    public Users signUpWithGoogle(GoogleSignUpRequest googleSignUpRequest) {
        try {
            Users existingUser = userRepository.findByUsername(googleSignUpRequest.getEmail());

            if (existingUser != null) {
                return existingUser;
            }
            Users user = new Users();
            user.setUsername(googleSignUpRequest.getEmail());

            System.out.println("Saving new user: " + user.getUsername());

            return userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Error during Google signup", e);
        }
    }
}
