package com.example.taskmasters.service;

import com.example.taskmasters.dto.EmailRequest;

public interface EmailService {
    void sendEmailAlert(EmailRequest emailRequest);
}
