package dogs.service;

import dogs.model.Dog;
import dogs.model.DogSize;
import dogs.service.exceptions.DogAlreadyExists;
import dogs.service.exceptions.DogNotFound;

import java.io.IOException;
import java.util.Collection;

public interface DogService {

    Collection<Dog> listAllDogs() throws IOException;

    Dog getDogById(String id) throws DogNotFound, IOException;

    void addDog(Dog dog) throws DogAlreadyExists,IOException;

    void updateDog(Dog dog) throws DogNotFound, IOException;

    void deleteDog(Dog dog) throws DogNotFound, IOException;

    Collection<Dog>listOnSize(DogSize s) throws  IOException;

}
