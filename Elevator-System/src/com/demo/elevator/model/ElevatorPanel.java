package com.demo.elevator.model;

public class ElevatorPanel {
    Elevator elevator;
    public ElevatorPanel(Elevator elevator){
        this.elevator = elevator;
    }
    private void selectFloor(int floorNumber){
        elevator.setFloorStop(floorNumber);
    }
    private void ringAlarm(){
        System.out.println("Alarm Ringing!!!");
    }
    private void stopElevator(){
        //Extra elevator status can be added for this scenario - like ERROR entry in enum
        System.out.println("Elevator Stopped!!");
    }
}
