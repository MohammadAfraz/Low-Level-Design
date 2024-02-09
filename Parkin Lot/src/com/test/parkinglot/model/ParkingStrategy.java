package com.test.parkinglot;

public interface ParkingStrategy {
    public void releaseParkingSpot(ParkingSpot parkingSpot);
    public ParkingSpot fetchParkingSpot(ParkingSpotType parkingSpotType);
}
