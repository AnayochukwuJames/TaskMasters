package com.example.taskmasters.dto;

import com.example.taskmasters.Enum.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpRequest {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String username;
    private String password;
    private Role role;

}
