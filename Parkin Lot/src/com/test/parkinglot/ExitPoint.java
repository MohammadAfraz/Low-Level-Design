package com.test.parkinglot;

import com.test.parkinglot.model.Ticket;
import com.test.parkinglot.service.ParkingLotService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ExitPoint {
    Integer HOURLY_FAIR = 30;
    public Double submitTicket(Ticket ticket){
        ParkingLotService.INSTANCE.releaseParkingSpot(ticket.getVehicleNumber());
        long seconds = Duration.between(ticket.getDateTime(), LocalDateTime.now()).get(ChronoUnit.SECONDS);
        return getFareForHours(seconds/3600);
    }
    private Double getFareForHours(long hours){
        return (double)(hours+1) * HOURLY_FAIR;
    }
}
