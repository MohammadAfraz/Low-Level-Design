import com.demo.bookmyshow.model.Address;
import com.demo.bookmyshow.model.Cinema;
import com.demo.bookmyshow.model.Screen;

public class Admin {
    Storage storage = Storage.Instance;
    public void addCinema(String cityId, String cinemaName, Address address){
        Cinema cinema = new Cinema(cinemaName, address);
        storage.addCinema(cityId, cinema);
    }

    public void addScreenToCinema(String cinemaId, String screenName, int capacity){
        Screen screen = new Screen(screenName, capacity);
        storage.addScreen(cinemaId, screen);
    }
}
