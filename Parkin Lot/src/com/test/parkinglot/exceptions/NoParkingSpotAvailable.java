package com.test.parkinglot.exceptions;

public class NoParkingSpotAvailable extends RuntimeException{
    public NoParkingSpotAvailable(String message){
        super(message);
    }
}
