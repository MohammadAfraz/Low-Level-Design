package com.test.parkinglot.exceptions;

public class IncorrectNumberOfParameters extends RuntimeException{
    public IncorrectNumberOfParameters(String message){
        super(message);
    }
}
