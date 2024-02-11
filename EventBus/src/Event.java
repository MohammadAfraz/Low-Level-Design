import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public class Event {
    String eventId;
    LocalDateTime dateTime;
    String publisher;
    public Event(String publisher){
        eventId = UUID.randomUUID().toString();
        dateTime = LocalDateTime.now();
        this.publisher = publisher;
    }

    public String getEventId() {
        return eventId;
    }
}
