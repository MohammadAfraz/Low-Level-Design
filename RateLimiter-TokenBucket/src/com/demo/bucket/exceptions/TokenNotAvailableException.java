package com.demo.bucket.exceptions;

public class TokenNotAvailableException extends RuntimeException {
    public TokenNotAvailableException(String message){
        super(message);
    }
}
