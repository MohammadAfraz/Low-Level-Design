import java.util.UUID;

public class Subscriber {
    String subscriberId;
    public Subscriber(){
        this.subscriberId = UUID.randomUUID().toString();
    }

    public String getSubscriberId() {
        return subscriberId;
    }
}
