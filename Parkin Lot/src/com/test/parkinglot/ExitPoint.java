package com.test.parkinglot;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ExitPoint {
    Integer HOURLY_FAIR = 30;
    public Double submitTicket(Ticket ticket){
        ParkingLotService.INSTANCE.releaseParkingSpot(ticket.getVehicleNumber());
        long seconds = Duration.between(ticket.getDateTime(), LocalDateTime.now()).get(ChronoUnit.SECONDS);
        long hours = seconds/3600;
        return getFareForHours(hours);
    }
    private Double getFareForHours(long hours){
        return (double)(hours+1) * HOURLY_FAIR;
    }
}
