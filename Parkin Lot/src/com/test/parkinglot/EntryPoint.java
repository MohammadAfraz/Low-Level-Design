package com.test.parkinglot;

import com.test.parkinglot.model.ParkingSpot;
import com.test.parkinglot.model.Ticket;
import com.test.parkinglot.model.VehicleType;
import com.test.parkinglot.service.ParkingLotService;

public class EntryPoint {
    public Ticket getTicket(String vehicleNumber, VehicleType vehicleType){
        ParkingSpot parkingSpot = ParkingLotService.INSTANCE.fetchParkingSpot(vehicleNumber, vehicleType);
        return generateTicket(parkingSpot.getId(), vehicleNumber);
    }

    private Ticket generateTicket(String parkingSpotId, String vehicleNumber){
        return new Ticket(parkingSpotId, vehicleNumber);
    }
}
