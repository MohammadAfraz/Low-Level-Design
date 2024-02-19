package com.test.parkinglot.model;

import java.util.UUID;

public class ParkingSpot {
    String id;
    ParkingSpotType parkingSpotType;
    Vehicle vehicle;

    //distance from the entrance, used in HeapParkingStrategy
    int distance;


    public ParkingSpot(ParkingSpotType parkingSpotType, int distance){
        this.id = UUID.randomUUID().toString();
        this.parkingSpotType = parkingSpotType;
        this.vehicle = null;
        this.distance = distance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ParkingSpotType getParkingSpotType() {
        return parkingSpotType;
    }

    public void setParkingSpotType(ParkingSpotType parkingSpotType) {
        this.parkingSpotType = parkingSpotType;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
