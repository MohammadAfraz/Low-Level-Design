package com.demo.elevator.model;

import java.util.List;

public interface ElevatorMovement {
    Elevator assignElevator(Direction direction, int floorNumber, List<Elevator> elevatorList);
}
