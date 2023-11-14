package com.chitchat.matchmaking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidUserException extends Exception{
    public InvalidUserException(String message) {
        super(message);
    }
}