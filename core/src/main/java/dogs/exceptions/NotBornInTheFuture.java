package dogs.exceptions;

public class NotBornInTheFuture extends Throwable {
    public NotBornInTheFuture(){
        super("Cannot be born for this date in the future!");
    }
}
