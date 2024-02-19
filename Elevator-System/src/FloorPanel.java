import com.demo.elevator.model.Direction;

import java.util.List;
import java.util.UUID;

public class FloorPanel {
    Direction direction;
    int floorNumber;
    String floorPanelId;
    ElevatorManager elevatorManager;
    public FloorPanel(int floorNumber){
        this.floorNumber = floorNumber;
        floorPanelId = UUID.randomUUID().toString();
        elevatorManager = ElevatorManager.INSTANCE;
    }

    public void call(Direction direction){
        elevatorManager.callLift(direction, floorNumber);
    }
}
