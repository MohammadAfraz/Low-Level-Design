package com.test.parkinglot.exceptions;

public class ParkingSpotNotFoundException extends RuntimeException{
    public ParkingSpotNotFoundException(String message){
        super(message);
    }
}
