import dogs.dao.DogDAO;
import dogs.dao.impl.DogDAOJSON;
import dogs.exceptions.IDNotMatching;
import dogs.exceptions.NotZeroOrNegative;
import dogs.model.Dog;
import dogs.model.DogSize;
import dogs.service.exceptions.DogAlreadyExists;
import dogs.service.exceptions.DogNotFound;
import dogs.service.impl.DogServiceImpl;
import org.easymock.EasyMock;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;

@RunWith(JUnit4.class)
public class ServiceTestWithData {

    @TestSubject
    private DogDAOJSON service;

    @Before
    public void setUp() throws IOException, DogAlreadyExists, NotZeroOrNegative, IDNotMatching {
        this.service = new DogDAOJSON("test2.json");
        service.createDog(new Dog("1222","Bodri",LocalDate.of(2012,10,11),DogSize.MIDDLING,35.2,16.2));
        service.createDog(new Dog("1223","Tappancs",LocalDate.of(2012,10,11),DogSize.MIDDLING,35.2,16.2));
        service.createDog(new Dog("1224","Zeusz",LocalDate.of(2012,10,11),DogSize.MIDDLING,35.2,16.2));
    }
    @Test
    public void DogsNumber() throws IOException {
        Collection<Dog> dogs = service.readDogs();
        Assert.assertEquals(3,dogs.size());
    }


    @Test
    public void readDogByID() throws IOException, DogNotFound, NotZeroOrNegative, IDNotMatching {
        Dog testDog = new Dog("1222","Bodri",LocalDate.of(2012,10,11),DogSize.MIDDLING,35.2,16.2);
        Dog refDog = service.readDogById("1222");
        Assert.assertEquals(testDog.getName(),refDog.getName());
    }

    @Test
    public void removeDog() throws IOException, DogNotFound, NotZeroOrNegative, IDNotMatching {
        Dog testDog = new Dog("1222","Bodri",LocalDate.of(2012,10,11),DogSize.MIDDLING,35.2,16.2);
        service.removeDog(testDog);
        try{
            service.readDogById("1222");
            Assert.assertEquals(0,service.readDogs().size());
        }catch (DogNotFound dnf){
            Assert.assertEquals(0,0);
        }
    }

    @After
    public void reset() throws IOException {
        File jsonfile = new File("test2.json");
        FileWriter writer = new FileWriter(jsonfile);
        writer.write("[]");
        writer.close();
    }


}
