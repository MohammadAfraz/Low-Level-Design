package com.test.parkinglot.model;

public class Car extends Vehicle {
    public Car(String carNumber){
        super(carNumber, VehicleType.CAR);
    }
}