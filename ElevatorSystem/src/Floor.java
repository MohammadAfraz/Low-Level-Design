import java.util.List;

public class Floor {
    int floorNumber;
    List<FloorPanel> floorPanels;
    public Floor(int floorNumber, int panels){
        this.floorNumber = floorNumber;
        for(int i=0; i<panels; i++){
            floorPanels.add(new FloorPanel(floorNumber));
        }
    }

    public List<FloorPanel> getFloorPanels(){
        return floorPanels;
    }
}