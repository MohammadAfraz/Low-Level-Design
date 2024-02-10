package com.test.parkinglot.model;

public interface ParkingStrategy {
    public void releaseParkingSpot(ParkingSpot parkingSpot);
    public ParkingSpot fetchParkingSpot(ParkingSpotType parkingSpotType);
}
