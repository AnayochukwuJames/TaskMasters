package com.example.taskmasters.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignUpResponse {
    private Long id;
    private String firstName;
    private String message;
    private String email;
    private HttpStatus httpStatus;

}
