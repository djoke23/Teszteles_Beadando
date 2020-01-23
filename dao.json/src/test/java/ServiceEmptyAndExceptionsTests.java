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
public class ServiceEmptyAndExceptionsTests {

    @TestSubject
    private DogDAOJSON service;

    @Before
    public void setUp() throws IOException {
        this.service = new DogDAOJSON("test2.json");
    }

    @Test
    public void DogsZeroNumber() throws IOException {
        Collection<Dog> dogs = service.readDogs();
        Assert.assertEquals(0,dogs.size());
    }

    @Test
    public void DogsAdd() throws NotZeroOrNegative, IDNotMatching {
        try{
            service.createDog(new Dog("1231","Kutymuty", LocalDate.of(2010,10,20), DogSize.SMALL,10.2,5.6));
            Assert.assertEquals(0,0);
        }catch (Exception ex){
            Assert.assertEquals(0,1);
        }
    }

    @Test
    public void readDogByIDExceptions(){
        try{
            service.readDogById("1231");
            Assert.assertEquals(0,1);
        }catch ( DogNotFound dnf ){
            Assert.assertEquals(0,0);
        }
        catch (IOException io){
            Assert.assertEquals(0,0);
        }
    }

    @Test
    public void removeDog() throws NotZeroOrNegative, IDNotMatching {
        try{
            service.removeDog(new Dog("1231","Kutymuty", LocalDate.of(2010,10,20), DogSize.SMALL,10.2,5.6) );
        }catch (DogNotFound dnf){
            Assert.assertEquals(0,0);
        }
        catch (IOException ex){
            Assert.assertEquals(0,0);
        }
    }

    @Test
    public void updateDog() throws NotZeroOrNegative, IDNotMatching {
        try{
            service.updateDog(new Dog("1231","Kutymuty", LocalDate.of(2010,10,20), DogSize.SMALL,10.2,5.6) );
        }catch (DogNotFound dnf){
            Assert.assertEquals(0,0);
        }
        catch (IOException ex){
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