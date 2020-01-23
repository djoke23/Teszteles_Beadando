package dogs.dao;

import dogs.model.Dog;
import dogs.model.DogSize;
import dogs.service.exceptions.DogAlreadyExists;
import dogs.service.exceptions.DogNotFound;

import java.io.IOException;
import java.util.Collection;

public interface DogDAO {
    Collection<Dog> readDogs() throws IOException;

    Dog readDogById(String id) throws IOException, DogNotFound;

    void createDog(Dog dog) throws IOException, DogAlreadyExists;

    void updateDog(Dog dog) throws  IOException, DogNotFound;

    void removeDog(Dog dog) throws  IOException, DogNotFound;

    Collection<Dog> listOnSize(DogSize s) throws IOException;
}
