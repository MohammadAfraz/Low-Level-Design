package com.test.parkinglot;

import com.test.parkinglot.exceptions.IncorrectNumberOfParameters;

import java.util.*;

public class HeapStrategy implements ParkingStrategy{
    Map<ParkingSpotType, PriorityQueue<ParkingSpot>> availableParkingSpots;

    //Ideally for each parking spot's distance from entrance, we need 1 more param to the constructor - List<<List<Integer>>> specifying distances of all spots
    //Initializing the distances in the constructor as of now
    public HeapStrategy(List<Integer> capacities){
        validate(capacities.size(), ParkingSpotType.values().length);
        ParkingSpotType[] parkingSpotTypes = ParkingSpotType.values();
        for(int i=0; i<capacities.size(); i++){
            if(capacities.get(i) > 0){
                PriorityQueue<ParkingSpot> parkingSpots = new PriorityQueue<>();
                for(int j=0; j < capacities.get(i); j++){
                    parkingSpots.add(new ParkingSpot(parkingSpotTypes[i], j+1));
                }
                availableParkingSpots.put(parkingSpotTypes[i], parkingSpots);
            }
        }
    }
    private void validate(int capacitiesProvided, int parkingSpotTypes){
        if(capacitiesProvided != parkingSpotTypes){
            throw new IncorrectNumberOfParameters("Capacity for all different parking spots has to be provided!");
        }
    }

    public void releaseParkingSpot(ParkingSpot parkingSpot){
        availableParkingSpots.get(parkingSpot.getParkingSpotType()).add(parkingSpot);
    }

    public ParkingSpot fetchParkingSpot(ParkingSpotType parkingSpotType){
        return availableParkingSpots.get(parkingSpotType).poll();
    }
}
