import com.demo.bookmyshow.model.Cinema;
import com.demo.bookmyshow.model.Payment;
import com.demo.bookmyshow.model.Screen;
import com.demo.bookmyshow.model.Show;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Storage {
    static Storage Instance = new Storage();

    private Map<String, Show> showMap;
    private Map<String, String> bookingPaymentMap;

    //Maps for searching purpose on UI
    //Cinemas have information about all the shows
    //Movie name can be searched from the cinema information on UI itself instead of calling backend
    private Map<String, List<Cinema>> cityToCinemasMap;


    private Storage() {
    }

    public void addCinema(String cityId, Cinema cinema){
        cityToCinemasMap.putIfAbsent(cityId, new ArrayList<>());
        cityToCinemasMap.get(cityId).add(cinema);
    }

    public void removeCinema(Cinema cinema){

    }

    public void addScreen(String cinemaId, Screen screen) {
    }

    public void removeScreen(String cinemaId, Screen screen){

    }


    public Show getShow(String showId) {
        return showMap.get(showId);
    }

    public void setPaymentIdMap(String bookingId, Payment payment) {
        bookingPaymentMap.put(bookingId, payment.getPaymentId());
    }

    public String getPaymentId(String bookingId){
        return bookingPaymentMap.get(bookingId);
    }

    public List<Cinema> getCinemas(String cityId){
        return cityToCinemasMap.get(cityId);
    }
}
