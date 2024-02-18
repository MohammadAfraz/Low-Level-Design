import java.util.UUID;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * DelayQueue uses getDelay() to fetch the waitTime/delayTime of the objects.
 * Apart from that it requires objects to be in ascending order of delayTime, internally it uses PriorityQueue to achieve that.
 * For ordering purpose, compareTo() has to be implemented, as specified by the interface Delayed.
 * Delayed extends Comparable
 */
public class DelayEvent implements Delayed {
    private final String id;
    private long consumptionTime;
    private TimeUnit timeUnit;

    public DelayEvent(TimeUnit timeUnit, long delayTime){
        id = UUID.randomUUID().toString();
        this.timeUnit = timeUnit;
        consumptionTime = timeUnit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS) + delayTime;
    }

    public long getConsumptionTime(){
        return consumptionTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(consumptionTime, timeUnit) - unit.convert(System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed obj) {
        return Math.toIntExact(this.consumptionTime - ((DelayEvent)obj).getConsumptionTime());
    }

    public String getId(){
        return id;
    }
}
