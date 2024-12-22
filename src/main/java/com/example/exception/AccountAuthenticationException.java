package com.example.exception;

public class AccountAuthenticationException extends RuntimeException {
    
    public AccountAuthenticationException(String message) {
        super(message);
    }
}
