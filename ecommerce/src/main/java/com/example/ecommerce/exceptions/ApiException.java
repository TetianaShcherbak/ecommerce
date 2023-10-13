package com.example.ecommerce.exceptions;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;


@AllArgsConstructor
public abstract class ApiException extends Exception{
    protected HttpStatus status;
    protected LocalDateTime timeStamp;
    protected String message;
}
