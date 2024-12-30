package com.fooddelivery.fooddeliveryapp.services;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserServiceException extends RuntimeException {
    public UserServiceException(String message) {
        super(message);
    }
}

