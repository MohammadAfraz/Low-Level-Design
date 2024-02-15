package com.demo.elevator.model;

import java.util.List;

public interface ElevatorMovement {
    void assignElevator(Direction direction, int floorNumber, List<Elevator> elevatorList);
}
