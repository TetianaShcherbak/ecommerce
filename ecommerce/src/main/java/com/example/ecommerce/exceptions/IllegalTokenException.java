package com.example.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;


public class IllegalTokenException extends ApiException{
    public IllegalTokenException() {
        super(HttpStatus.UNAUTHORIZED, LocalDateTime.now(), "Token doesn't exist or had already expired");
    }
}
