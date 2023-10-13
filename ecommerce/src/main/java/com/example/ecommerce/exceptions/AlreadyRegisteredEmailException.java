package com.example.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;


public class AlreadyRegisteredEmailException extends ApiException{
    public AlreadyRegisteredEmailException(String email){
        super(HttpStatus.OK, LocalDateTime.now(), "Email " + email + " has already been taken!");
    }
}
