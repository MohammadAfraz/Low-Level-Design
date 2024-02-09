package com.test.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotService {
    ParkingFloor parkingFloor = new ParkingFloor(List.of(3, 3), ParkingStrategyType.DEQUE);
    static final ParkingLotService INSTANCE = new ParkingLotService();
    public ParkingSpot fetchParkingSpot(String vehicleNumber, VehicleType vehicleType){
        Vehicle vehicle = getVehicle(vehicleNumber, vehicleType);
        ParkingSpot parkingSpot = parkingFloor.fetchParkingSpot(vehicleType);
        StorageService.INSTANCE.addVehicleToParkingSpotMapping(vehicleNumber, parkingSpot.getId());
        StorageService.INSTANCE.addParkingIdToParkingSpotMapping(parkingSpot.getId(), parkingSpot);
        StorageService.INSTANCE.addVehicleIdToVehicleMapping(vehicleNumber, vehicle);
        return parkingSpot;
    }

    public void releaseParkingSpot(String vehicleNumber){
        ParkingSpot parkingSpot = StorageService.INSTANCE.getParkingSpotFromVehicleNumber(vehicleNumber);
        parkingFloor.releaseParkingSpot(parkingSpot);
        StorageService.INSTANCE.removeVehicleToParkingSpotMapping(vehicleNumber);
        StorageService.INSTANCE.removeVehicleIdToVehicleMapping(vehicleNumber);
    }
    private Vehicle getVehicle(String vehicleNumber, VehicleType vehicleType){
        Vehicle vehicle;
        switch(vehicleType){
            case BIKE:{
                vehicle = new Bike(vehicleNumber);
            }
            default:{
                vehicle = new Car(vehicleNumber);
            }
        }
        return vehicle;
    }
}
