package com.test.parkinglot;

public abstract class Vehicle {
    String number;
    VehicleType vehicleType;

    public Vehicle(String vehicleNumber, VehicleType vehicleType){
        this.number = vehicleNumber;
        this.vehicleType = vehicleType;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }
}
