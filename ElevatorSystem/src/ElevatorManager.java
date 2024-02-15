import com.demo.elevator.model.Direction;
import com.demo.elevator.model.Elevator;

import java.util.ArrayList;
import java.util.List;

public class ElevatorManager {
    List<Elevator> elevatorList = new ArrayList<>();
    OptimizedScan optimizedScan = new OptimizedScan();

    static ElevatorManager INSTANCE = new ElevatorManager();
    int floorCount;
    private ElevatorManager(){

    }
    public void addElevators(int elevators, int floorCount){
        this.floorCount = floorCount;
        for(int i=0; i<elevators; i++){
            elevatorList.add(new Elevator(this.floorCount));
        }
    }
    public void callLift(Direction direction, int floorNumber){
        optimizedScan.assignElevator(direction, floorNumber, elevatorList);
    }
}
