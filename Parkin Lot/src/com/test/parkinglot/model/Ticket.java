package com.test.parkinglot.model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

public class Ticket {
    String parkingSpotId;
    String vehicleNumber;
    LocalDateTime dateTime;

    public Ticket(String parkingSpotId, String vehicleNumber){
        this.parkingSpotId = parkingSpotId;
        this.vehicleNumber = vehicleNumber;
        dateTime =  LocalDateTime.now();
    }


    public void setParkingSpotId(String parkingSpotId) {
        this.parkingSpotId = parkingSpotId;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getParkingSpotId() {
        return parkingSpotId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
