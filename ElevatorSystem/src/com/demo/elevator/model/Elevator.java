package com.demo.elevator.model;

import java.util.concurrent.atomic.AtomicInteger;

public class Elevator {
    Direction direction;
    AtomicInteger currentFloor;
    boolean floorStops[];
    AtomicInteger minFloorToReach, maxFloorToReach;
    public Elevator(int floors){
        floorStops = new boolean[floors];
        minFloorToReach = new AtomicInteger(Integer.MAX_VALUE);
        maxFloorToReach = new AtomicInteger(Integer.MIN_VALUE);
    }

    public void updateCurrentFloor(int floorNumber){
        this.currentFloor.set(floorNumber);
        if(floorNumber == minFloorToReach.get()){
            minFloorToReach.set(Integer.MAX_VALUE);
            if(maxFloorToReach.get() != Integer.MIN_VALUE){
                direction = Direction.UP;
            }
            else{
                direction = Direction.IDLE;
            }
        }
        else if(floorNumber == maxFloorToReach.get()){
            maxFloorToReach.set(Integer.MIN_VALUE);
            if(minFloorToReach.get() != Integer.MAX_VALUE){
                direction = Direction.DOWN;
            }
            else{
                direction = Direction.IDLE;
            }
        }
        if(floorStops[floorNumber]){
            System.out.println("Door Opened!! Will be closing in 30 seconds!");
        }
        floorStops[floorNumber] = false;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getCurrentFloor() {
        return currentFloor.get();
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor.set(currentFloor);
    }

    public boolean[] getFloorStops() {
        return floorStops;
    }

    public void setFloorStop(int floorStop) {
        this.floorStops[floorStop] = true;
        if(floorStop < minFloorToReach.get()){
            minFloorToReach.set(floorStop);
        }
        if(floorStop > maxFloorToReach.get()){
            maxFloorToReach.set(floorStop);
        }
    }

    public int getMinFloorToReach() {
        return minFloorToReach.get();
    }

    public void setMinFloorToReach(int minFloorToReach) {
        this.minFloorToReach.set(minFloorToReach);
    }

    public int getMaxFloorToReach() {
        return maxFloorToReach.get();
    }

    public void setMaxFloorToReach(int maxFloorToReach) {
        this.maxFloorToReach.set(maxFloorToReach);
    }

    public void move(Direction direction){
        System.out.println("Move in direction: "+direction);
        AtomicInteger target = direction.UP.equals(direction) ? maxFloorToReach : minFloorToReach;
        for(int i=currentFloor.get(); i<target.get(); i++){
            //Keep on moving to next floor, perform validation at each floor.
            //Open the door if required, functionality included in below function
            //target can be updated while lift is moving, so elevator will be moving to the updated target
            updateCurrentFloor(i+1);
        }
    }
}
