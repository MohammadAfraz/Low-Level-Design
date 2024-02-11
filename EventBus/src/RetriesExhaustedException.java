public class RetriesExhaustedException extends RuntimeException{
    public RetriesExhaustedException(String message){
        super(message);
    }
    public RetriesExhaustedException(Throwable t){
        super(t);
    }
}
