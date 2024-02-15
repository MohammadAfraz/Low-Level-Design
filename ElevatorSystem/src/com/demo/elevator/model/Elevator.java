package com.demo.elevator.model;

public class Elevator {
    Direction direction;
    int currentFloor;
    boolean floorStops[];
    int minFloorToReach, maxFloorToReach;
    public Elevator(int floors){
        floorStops = new boolean[floors];
        minFloorToReach = Integer.MAX_VALUE;
        maxFloorToReach = Integer.MIN_VALUE;
    }

    public void updateCurrentFloor(int floorNumber){
        this.currentFloor = floorNumber;
        if(floorNumber == minFloorToReach){
            minFloorToReach = Integer.MAX_VALUE;
            if(maxFloorToReach != Integer.MIN_VALUE){
                direction = Direction.UP;
            }
            else{
                direction = Direction.IDLE;
            }
        }
        else if(floorNumber == maxFloorToReach){
            maxFloorToReach = Integer.MIN_VALUE;
            if(minFloorToReach != Integer.MAX_VALUE){
                direction = Direction.DOWN;
            }
            else{
                direction = Direction.IDLE;
            }
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
        return currentFloor;
    }

    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    public boolean[] getFloorStops() {
        return floorStops;
    }

    public void setFloorStop(int floorStop) {
        this.floorStops[floorStop] = true;
    }

    public int getMinFloorToReach() {
        return minFloorToReach;
    }

    public void setMinFloorToReach(int minFloorToReach) {
        this.minFloorToReach = minFloorToReach;
    }

    public int getMaxFloorToReach() {
        return maxFloorToReach;
    }

    public void setMaxFloorToReach(int maxFloorToReach) {
        this.maxFloorToReach = maxFloorToReach;
    }
}
