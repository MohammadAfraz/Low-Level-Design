package com.test.parkinglot;

public class EntryPoint {
    public Ticket getTicket(String vehicleNumber, VehicleType vehicleType){
        ParkingSpot parkingSpot = ParkingLotService.INSTANCE.fetchParkingSpot(vehicleNumber, vehicleType);
        return generateTicket(parkingSpot.getId(), vehicleNumber);
    }

    private Ticket generateTicket(String parkingSpotId, String vehicleNumber){
        return new Ticket(parkingSpotId, vehicleNumber);
    }
}
