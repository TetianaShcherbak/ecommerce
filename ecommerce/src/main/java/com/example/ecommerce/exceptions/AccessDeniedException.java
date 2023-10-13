package com.example.ecommerce.exceptions;

import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;


public class AccessDeniedException extends ApiException {
    public AccessDeniedException() {
        super(HttpStatus.FORBIDDEN, LocalDateTime.now(), "Uupss... Access Denied");
    }
}
