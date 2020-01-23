package dogs.controller;

import dogs.model.Dog;
import dogs.model.DogSize;
import dogs.service.DogService;
import dogs.service.exceptions.DogAlreadyExists;
import dogs.service.exceptions.DogNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;

@Controller
public class DogController {
    DogService service;

    public DogController(@Autowired DogService service){this.service = service;}

    @RequestMapping(value = "/hello")
    @ResponseBody
    public String hello(){
        return "Hello";
    }

    @RequestMapping(value = "/count")
    @ResponseBody
    public int getDogsNumber() throws IOException {
        return service.listAllDogs().size();
    }

    @RequestMapping(value = "/listDogs")
    @ResponseBody
    public Collection<Dog> listDog() throws IOException {
        return service.listAllDogs();
    }

    @RequestMapping(value = "/list/{size}")
    @ResponseBody
    public Collection<Dog> listOnThisSize(@PathVariable DogSize size) throws IOException {
        return service.listOnSize(size);
    }


    @RequestMapping(value = "/addDog",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addDog(@RequestBody Dog dog) throws IOException, DogAlreadyExists {
        service.addDog(dog);
        return "New Dog added: "+dog.getId();
    }

    @RequestMapping(value = "/dog/{id:[A-Za-z0-9]{4}}")
    @ResponseBody
    public Dog getDogByID(@PathVariable String id) throws IOException, DogNotFound {

        return service.getDogById(id);
    }
}
