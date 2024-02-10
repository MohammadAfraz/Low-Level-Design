package com.test.parkinglot;

import com.test.parkinglot.model.ParkingSpot;
import com.test.parkinglot.model.ParkingStrategy;
import com.test.parkinglot.model.ParkingStrategyType;
import com.test.parkinglot.model.VehicleType;
import com.test.parkinglot.service.StorageService;

import java.util.List;

public class ParkingFloor {
    private ParkingStrategy parkingStrategy;

    public ParkingFloor(List<Integer> capacities, ParkingStrategyType parkingStrategyType){
        initializeParkingStrategy(capacities, parkingStrategyType);
    }
    private void initializeParkingStrategy(List<Integer> capacities, ParkingStrategyType parkingStrategyType){
        switch (parkingStrategyType){
            case HEAP:{
                parkingStrategy = new HeapStrategy(capacities);
            }
            default:{
                parkingStrategy = new DoublyLinkedListStrategy(capacities);
            }
        }
    }

    public ParkingSpot fetchParkingSpot(VehicleType vehicleType){
        return  parkingStrategy.fetchParkingSpot(StorageService.INSTANCE.getParkingSpotType(vehicleType));
    }

    public void releaseParkingSpot(ParkingSpot parkingSpot){
        parkingStrategy.releaseParkingSpot(parkingSpot);
    }
}
