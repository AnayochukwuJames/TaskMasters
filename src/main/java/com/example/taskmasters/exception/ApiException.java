package com.example.taskmasters.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
public class ApiException {
    private String message;
    private HttpStatus status;
    private ZonedDateTime timeStamp;
}
