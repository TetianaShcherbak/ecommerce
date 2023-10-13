package com.example.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;


public class UserNotFoundException extends ApiException{
    public UserNotFoundException() {
        super(HttpStatus.I_AM_A_TEAPOT, LocalDateTime.now(), "User was not found in database");
    }
}
