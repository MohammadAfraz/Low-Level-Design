import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.function.Supplier;

public class HashedExecutor<K> {
    private List<ExecutorService> executors;

    public HashedExecutor(int totalExecutors){
        executors = new ArrayList<>();
        for(int i=0; i<totalExecutors; i++){
            executors.add(Executors.newSingleThreadExecutor());
        }
    }

    public <U> CompletionStage<U> execute(String hashKey, CompletionStage<U> task){
        int hashCode = Math.abs(hashKey.hashCode());
        return CompletableFuture.supplyAsync(() -> task , executors.get(hashCode % executors.size())).thenCompose(Function.identity());
    }

    public void shutDown(){
        executors.stream().forEach(executorService -> executorService.shutdown());
    }
}
