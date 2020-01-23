package dogs.service.exceptions;

public class DogNotFound extends Exception {
    public DogNotFound(String id) {
        super(id);
    }
}
