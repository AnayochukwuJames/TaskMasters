package com.example.taskmasters.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class MessageServices {

    private final JavaMailSender javaMailSender;
    public void loginNotification(String receiver, String message) throws MessagingException {
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true,"utf-8");
        mimeMessageHelper.setTo(receiver);
        mimeMessageHelper.setSubject("Login Notification");
        mimeMessageHelper.setText(message);
        javaMailSender.send(mimeMessageHelper.getMimeMessage());
    }
}
