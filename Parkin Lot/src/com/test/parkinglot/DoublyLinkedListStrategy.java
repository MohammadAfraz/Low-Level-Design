package com.test.parkinglot;

import com.test.parkinglot.exceptions.IncorrectNumberOfParameters;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DoublyLinkedListStrategy implements ParkingStrategy{
    Map<ParkingSpotType, Deque<ParkingSpot>> availableParkingSpots;
    public DoublyLinkedListStrategy(List<Integer> capacities){
        validate(capacities.size(), ParkingSpotType.values().length);
        ParkingSpotType[] parkingSpotTypes = ParkingSpotType.values();
        for(int i=0; i<capacities.size(); i++){
            if(capacities.get(i) > 0){
                Deque<ParkingSpot> parkingSpotsLinkedList = new LinkedList<>();
                for(int j=0; j < capacities.get(i); j++){
                    parkingSpotsLinkedList.addLast(new ParkingSpot(parkingSpotTypes[i], 0));//spot distance is not required for this strategy
                }
                availableParkingSpots.put(parkingSpotTypes[i], parkingSpotsLinkedList);
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
        return availableParkingSpots.get(parkingSpotType).getFirst();
    }
}
