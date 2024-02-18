import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class EventProducer implements Runnable{
    BlockingQueue<DelayEvent> delayedQueue;
    int totalEventsToProduce;
    TimeUnit timeUnit;
    long delayTime;
    public EventProducer(BlockingQueue delayedQueue, TimeUnit timeUnit, long delayTime, int totalEventsToProduce){
        this.delayedQueue = delayedQueue;
        this.timeUnit = timeUnit;
        this.delayTime = delayTime;
        this.totalEventsToProduce = totalEventsToProduce;
    }

    public void run(){
        for(int i=0; i<totalEventsToProduce; i++){
            DelayEvent delayEvent = new DelayEvent(timeUnit, delayTime);
            try {
                delayedQueue.put(delayEvent);
                //Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Added delayed event: "+delayEvent.getId());
        }
    }
}
