package dogs.service.impl;

import dogs.dao.DogDAO;
import dogs.model.Dog;
import dogs.model.DogSize;
import dogs.service.DogService;
import dogs.service.exceptions.DogAlreadyExists;
import dogs.service.exceptions.DogNotFound;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DogServiceImpl implements DogService {
    DogDAO dao;

    public DogServiceImpl(DogDAO dao) { this.dao = dao; }

    public Collection<Dog> listAllDogs() throws IOException {
        return dao.readDogs();
    }

    public Dog getDogById(String id) throws DogNotFound, IOException {
        return dao.readDogById(id);
    }

    public void addDog(Dog dog) throws DogAlreadyExists, IOException {
        dao.createDog(dog);
    }

    public void updateDog(Dog dog) throws DogNotFound,  IOException {
        dao.updateDog(dog);
    }

    public void deleteDog(Dog dog) throws DogNotFound, IOException {
        dao.removeDog(dog);
    }

    public Collection<Dog> listOnSize(DogSize s) throws IOException {
        return dao.listOnSize(s);
    }

    public Dog getDogByIdDAO(String id) throws DogNotFound,IOException{
        Collection<Dog> dogs = dao.readDogs();
        for(Dog d : dogs){
            if(d.getId() == id){
                return d;
            }
        }
        throw new DogNotFound(id);
    }
    public List<Dog> addDogDAO (Dog dog) throws IOException, DogAlreadyExists {
        List<Dog> dogs = new ArrayList<Dog>(dao.readDogs());
        for(Dog d : dogs){
            if(dog.getId() == d.getId()){
                throw new DogAlreadyExists(dog.getId());
            }
        }
        dogs.add(dog);
        return dogs;
    }

    public List<Dog> deleteDogDAO(Dog dog) throws IOException, DogNotFound {
        List<Dog> dogs = new ArrayList<>(dao.readDogs());
        for(Dog d : dogs){
            if(d.getId() == dog.getId()){
                dogs.remove(d);
                return dogs;
            }
        }
        throw  new DogNotFound(dog.getId());

    }

    public Collection<Dog> listOnSizeDAO(DogSize s) throws IOException {
        List<Dog> sizedDog = new ArrayList<Dog>();
        Collection<Dog> dogs = dao.readDogs();
        for(Dog d : dogs){
            if(d.getSize() == s){
                sizedDog.add(d);
            }
        }

        return sizedDog;
    }
}
