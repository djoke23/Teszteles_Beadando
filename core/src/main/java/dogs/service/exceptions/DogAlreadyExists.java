package dogs.service.exceptions;

public class DogAlreadyExists extends Exception {
    public DogAlreadyExists(String id) {
        super(id);
    }
}
