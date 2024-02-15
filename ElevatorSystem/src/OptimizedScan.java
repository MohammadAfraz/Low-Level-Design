import com.demo.elevator.model.Direction;
import com.demo.elevator.model.Elevator;
import com.demo.elevator.model.ElevatorMovement;

import java.util.List;

public class OptimizedScan implements ElevatorMovement {

    @Override
    public Elevator assignElevator(Direction direction, int floorNumber, List<Elevator> elevatorList) {
        int distance = Integer.MAX_VALUE;
        Elevator selectedElevator = null;
        for(Elevator elevator : elevatorList){
            if(elevator.getCurrentFloor() != floorNumber){
                int currentDistance = floorNumber - elevator.getCurrentFloor();
                if(direction.equals(elevator.getDirection())){//Call direction is Up or Down
                    if(Direction.UP.equals(elevator.getDirection())){
                        if(currentDistance > 0 && currentDistance < distance){//lift should be coming towards floor, shouldn't have crossed it.
                            selectedElevator = elevator;
                            distance = currentDistance;
                        }
                    }
                    else if(Direction.DOWN.equals(elevator.getDirection())){
                        if(currentDistance < 0 && currentDistance < distance){//lift should be coming towards floor, shouldn't have crossed it.
                            selectedElevator = elevator;
                            distance = Math.abs(currentDistance);
                        }
                    }
                }
                else if(!Direction.IDLE.equals(elevator.getDirection())){
                    //If the call direction is opposite to the lift direction, we assign based on last target floor of the lift.
                    if(Direction.UP.equals(elevator.getDirection()) && Math.abs(elevator.getMaxFloorToReach()-floorNumber) < distance){
                         selectedElevator = elevator;
                         distance = Math.abs(elevator.getMaxFloorToReach()-floorNumber);
                    }
                    else if(Math.abs(elevator.getMinFloorToReach()-floorNumber) < distance){
                        selectedElevator = elevator;
                        distance = Math.abs(elevator.getMaxFloorToReach()-floorNumber);
                    }
                }
                if(Direction.IDLE.equals(elevator.getDirection()) && distance > currentDistance){
                    selectedElevator = elevator;
                    distance = currentDistance;
                }
            }
        }
        if(selectedElevator != null){
            selectedElevator.setFloorStop(floorNumber);
        }
        return selectedElevator;
    }
}
