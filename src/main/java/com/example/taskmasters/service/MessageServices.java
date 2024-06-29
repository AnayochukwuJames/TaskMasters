package com.example.taskmasters.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class MessageServices {

    private final JavaMailSender javaMailSender;


        @Async
        public void loginNotification(String receiver, String message) throws MessagingException {
            MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true, "utf-8");
            mimeMessageHelper.setTo(receiver);
            mimeMessageHelper.setSubject("Login Notification");
            mimeMessageHelper.setText(message);
            javaMailSender.send(mimeMessageHelper.getMimeMessage());
        }

        @Async
        public void registrationNotification(String receiver, String firstName) throws MessagingException {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            messageHelper.setTo(receiver);
            messageHelper.setSubject("Registration Successful!");
            String message = String.format("Dear %s,\nCongratulations!\nYou have successfully registered with Email address: %s. " +
                    "\n Your Account Number is " +firstName, receiver);
            messageHelper.setText(message);
            javaMailSender.send(messageHelper.getMimeMessage());
        }
    }