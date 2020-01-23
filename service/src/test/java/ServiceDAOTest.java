import dogs.dao.DogDAO;
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
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
@RunWith(JUnit4.class)
public class ServiceDAOTest {

    @Mock
    public DogDAO dao;

    @TestSubject
    private DogServiceImpl service;

    @Before
    public void setUp() throws IOException, NotZeroOrNegative, IDNotMatching, DogNotFound {
        dao = EasyMock.niceMock(DogDAO.class);
        this.service = new DogServiceImpl(dao);
        Collection<Dog> doggys = Arrays.asList(new Dog("1223","Suzy",LocalDate.of(2010,12,3),DogSize.LARGE,27.8,15.2));
        EasyMock.expect(dao.readDogs()).andReturn(doggys).anyTimes();
        Dog testDog = new Dog("1223","Suzy",LocalDate.of(2010,12,3),DogSize.LARGE,27.8,15.2);
        EasyMock.expect(dao.readDogById("1223")).andReturn(testDog).anyTimes();
        EasyMock.expect(dao.listOnSize(DogSize.LARGE)).andReturn(doggys).anyTimes();
        EasyMock.replay(dao);
    }
    @Test
    public void createDog() throws DogAlreadyExists, IOException, NotZeroOrNegative, IDNotMatching {
        try {
            service.addDog(new Dog("1222", "Bodri", LocalDate.of(2009, 1, 23), DogSize.LARGE, 40.3, 20.3));
            Assert.assertEquals(0, 0);
        } catch (Exception ex) {
            Assert.assertEquals(0, 1);
        }
    }
    @Test
    public void listAllDogs() throws IOException {
        Collection<Dog> dummy = service.listAllDogs();
        Assert.assertEquals(1,dummy.size());
    }
    @Test
    public void getDogById() throws IOException, DogNotFound {
            Assert.assertEquals("Suzy",service.getDogById("1223").getName());
    }

    @Test
    public void listAllDogOnSize() throws IOException {
        Assert.assertEquals(1,service.listAllDogs().size());
    }
    @Test
    public void deleteDog(){
        try{
            service.deleteDog(new Dog());
            Assert.assertEquals(0,0);
        }catch (Exception ex){
            Assert.assertEquals(0,1);
        }
    }

    @Test
    public void getDogByIdDAO() throws IOException, DogNotFound {
        Assert.assertEquals("Suzy",service.getDogByIdDAO("1223").getName());
    }

    @Test(expected = DogNotFound.class)
    public void getDogByIdDAOButNotFound() throws IOException, DogNotFound {
        service.getDogByIdDAO("1222");
    }

    @Test
    public void addDogDAO() throws IOException, DogAlreadyExists, NotZeroOrNegative, IDNotMatching {
        Dog testdog = new Dog("1224","Boby",LocalDate.of(2010,12,3),DogSize.MIDDLING,15.2,4.2);
        Assert.assertEquals(2,service.addDogDAO(testdog).size());
    }

    @Test(expected = DogAlreadyExists.class)
    public void addDogDAOButAlready() throws IOException, DogAlreadyExists, NotZeroOrNegative, IDNotMatching {
        Dog testdog = new Dog("1223","Boby",LocalDate.of(2010,12,3),DogSize.MIDDLING,15.2,4.2);
        service.addDogDAO(testdog);
    }

    @Test
    public void listAllDogOnSizeDAO() throws IOException {
        Assert.assertEquals(1,service.listOnSizeDAO(DogSize.LARGE).size());
    }

    @Test
    public void deleteDogDao() throws NotZeroOrNegative, IDNotMatching, IOException, DogNotFound {
        Dog testdog = new Dog("1223","Suzy",LocalDate.of(2010,12,3),DogSize.LARGE,27.8,15.2);
        Assert.assertEquals(0,service.deleteDogDAO(testdog).size());
    }

    @Test(expected = DogNotFound.class)
    public void deleteDogDAOButNotFound() throws IOException, DogAlreadyExists, NotZeroOrNegative, IDNotMatching, DogNotFound {
        Dog testdog = new Dog("1224","Boby",LocalDate.of(2010,12,3),DogSize.MIDDLING,15.2,4.2);
        service.deleteDogDAO(testdog);
    }


}


