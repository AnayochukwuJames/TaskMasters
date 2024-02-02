package com.example.taskmasters.service.serviceImp;

import com.example.taskmasters.dto.EmailRequest;
import com.example.taskmasters.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor

public class EmailServiceImpl implements EmailService {
    @Override
    public void sendEmailAlert(EmailRequest emailRequest) {

    }


}