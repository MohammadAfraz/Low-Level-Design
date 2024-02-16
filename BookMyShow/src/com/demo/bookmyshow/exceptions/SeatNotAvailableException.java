package com.demo.bookmyshow.exceptions;

public class SeatNotAvailableException extends RuntimeException{
    public SeatNotAvailableException(String message){
        super(message);
    }
}
