package com.example.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;

public class OrderNotFoundException extends ApiException{
    public OrderNotFoundException() {
        super(HttpStatus.NOT_FOUND, LocalDateTime.now(), "Order was not found");
    }
}
