import java.util.concurrent.*;

public class Driver {
    public static void main(String args[]) throws InterruptedException {
        BlockingQueue<DelayEvent> delayQueue = new DelayQueue<>();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(new EventProducer(delayQueue, TimeUnit.MILLISECONDS, 1000, 3));
        executorService.submit(new EventConsumer(delayQueue, 3));
        executorService.awaitTermination(1500, TimeUnit.MILLISECONDS);
        executorService.shutdownNow();
    }
}
