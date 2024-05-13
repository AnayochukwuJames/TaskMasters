package com.example.taskmasters.dto;

import com.example.taskmasters.entities.Users;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private Users users;
    private String token;
}
