import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class EventConsumer implements Runnable{
    BlockingQueue<DelayEvent> delayQueue;
    int totalEventsToConsume;


    public EventConsumer(BlockingQueue delayQueue, int totalEventsToConsume){
        this.delayQueue = delayQueue;
        this.totalEventsToConsume = totalEventsToConsume;
    }
    public void run(){
        for(int i=0; i<totalEventsToConsume; i++){
            try{
                Object take = delayQueue.take();
                System.out.println("Consumed delayed event: "+((DelayEvent) take).getId());
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
