package com.example.taskmasters.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(BadRequestException badRequestException) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException();
        apiException.setMessage(badRequestException.getMessage());
        apiException.setStatus(httpStatus);
        apiException.setTimeStamp(ZonedDateTime.now());

        return new ResponseEntity<>(apiException, httpStatus);
    }
}
