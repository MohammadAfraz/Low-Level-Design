package com.test.parkinglot.service;

import com.test.parkinglot.exceptions.ParkingSpotNotFoundException;
import com.test.parkinglot.model.ParkingSpot;
import com.test.parkinglot.model.ParkingSpotType;
import com.test.parkinglot.model.Vehicle;
import com.test.parkinglot.model.VehicleType;

import java.util.HashMap;
import java.util.Map;

public class StorageService {
    final Map<VehicleType, ParkingSpotType> typeMap = new HashMap<>();
    Map<String, String> vehicleToSpotMap;
    Map<String, Vehicle> idToVehicleMap;
    Map<String, ParkingSpot> idToParkingSpotMap;

    public static final StorageService INSTANCE = new StorageService();
    public StorageService(){
        initializeVehicleTypeToSpotTypeMapping();
    }

    private void initializeVehicleTypeToSpotTypeMapping(){
        for(VehicleType vehicleType : VehicleType.values()){
            switch (vehicleType){
                case CAR:{
                    typeMap.put(VehicleType.CAR, ParkingSpotType.CAR);
                    break;
                }
                default:{
                    typeMap.put(VehicleType.BIKE, ParkingSpotType.BIKE);
                }
            }
        }
    }

    public ParkingSpotType getParkingSpotType(VehicleType vehicleType){
        return typeMap.get(vehicleType);
    }

    public void addVehicleToParkingSpotMapping(String vehicleNumber, String parkingSpotId){
        vehicleToSpotMap.put(vehicleNumber, parkingSpotId);
    }

    public void addVehicleIdToVehicleMapping(String vehicleNumber, Vehicle vehicle){
        idToVehicleMap.put(vehicleNumber, vehicle);
    }

    public void addParkingIdToParkingSpotMapping(String parkingSpotId, ParkingSpot parkingSpot){
        idToParkingSpotMap.put(parkingSpotId, parkingSpot);
    }

    public void removeVehicleToParkingSpotMapping(String vehicleNumber){
        vehicleToSpotMap.remove(vehicleNumber);
    }

    public void removeVehicleIdToVehicleMapping(String vehicleNumber){
        idToVehicleMap.remove(vehicleNumber);
    }

    public String getParkingSpotId(String vehicleNumber){
        return vehicleToSpotMap.get(vehicleNumber);
    }

    public ParkingSpot getParkingSpotFromVehicleNumber(String vehicleNumber){
        String parkingSpotId = vehicleToSpotMap.get(vehicleNumber);
        if(parkingSpotId == null){
            throw new ParkingSpotNotFoundException("ParkingSpot is not present for given vehicleNumber");
        }
        return idToParkingSpotMap.get(parkingSpotId);

    }

    public ParkingSpot getParkingSpot(String parkingSpotId){
        return  idToParkingSpotMap.get(parkingSpotId);
    }

    public Vehicle getVehicle(String vehicleNumber){
        return idToVehicleMap.get(vehicleNumber);
    }
}
