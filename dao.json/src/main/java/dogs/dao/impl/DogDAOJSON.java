package dogs.dao.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dogs.dao.DogDAO;
import dogs.model.Dog;
import dogs.model.DogSize;
import dogs.service.exceptions.DogAlreadyExists;
import dogs.service.exceptions.DogNotFound;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DogDAOJSON implements DogDAO {
    File jsonfile;
    ObjectMapper mapper;

    public DogDAOJSON(String filepath) throws IOException {
        jsonfile = new File(filepath);
        if (!jsonfile.exists()) {
            jsonfile.createNewFile();
            FileWriter writer = new FileWriter(jsonfile);
            writer.write("[]");
            writer.close();
        }
        if (jsonfile.getTotalSpace() <= 0) {
            FileWriter writer = new FileWriter(jsonfile);
            writer.write("[]");
            writer.close();
        }
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
    }

    public Collection<Dog> readDogs() throws IOException {
        Collection<Dog> result = new ArrayList<Dog>();
        TypeReference type = new TypeReference<ArrayList<Dog>>() {
        };
        result = mapper.readValue(jsonfile, type);
        return result;
    }

    public Dog readDogById(String id) throws IOException, DogNotFound {
        Collection<Dog> dogs = readDogs();
        for (Dog d : dogs) {
            if(d.getId().equalsIgnoreCase(id)){
                return d;
            }
        }
        throw new DogNotFound(id);
    }


    public void createDog(Dog dog) throws IOException, DogAlreadyExists {
        Collection<Dog> dogs = readDogs();
        try {
            readDogById(dog.getId());
            throw new DogAlreadyExists(dog.getId());
        } catch (DogNotFound carNotFound) {
            dogs.add(dog);
            mapper.writeValue(jsonfile, dogs);
        }
    }

    public void updateDog(Dog dog) throws IOException, DogNotFound {
        Collection<Dog> dogs = readDogs();
        Dog d = readDogById(dog.getId());
        List<Dog> doglist = new ArrayList<Dog>(dogs);
        int index = doglist.indexOf(d);
        doglist.set(index, dog);
        mapper.writeValue(jsonfile,doglist);
    }

    public void removeDog(Dog dog) throws IOException, DogNotFound {
        List<Dog> dogs = new ArrayList<Dog>(readDogs());
        int index = 0;
        for(int i = 0; i< dogs.size(); i++){
            if(dog.getId() == dogs.get(i).getId()){
                index = i;
            }
        }
        dogs.remove(index);
        mapper.writeValue(jsonfile, dogs);
    }

    public Collection<Dog> listOnSize(DogSize s) throws IOException {
        List<Dog> dogs = new ArrayList<Dog>(readDogs());
        List<Dog> tmp = new ArrayList<Dog>();
        for(int i = 0; i< dogs.size(); i++){
            if(dogs.get(i).getSize() == s){
                tmp.add(dogs.get(i));
            }
        }
        return tmp;
    }
}
