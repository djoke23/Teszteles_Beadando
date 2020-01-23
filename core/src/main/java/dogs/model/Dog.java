package dogs.model;

import dogs.exceptions.IDNotMatching;
import dogs.exceptions.NotBornInTheFuture;
import dogs.exceptions.NotZeroOrNegative;

import java.time.LocalDate;

public class Dog {
    private String id;
    private String name;
    private LocalDate date_of_born;
    private DogSize size;
    private double heigth;
    private double weigth;

    public Dog() {
    }

    public Dog(String id, String name, LocalDate date_of_born, DogSize size, double heigth, double weigth) throws IDNotMatching, NotZeroOrNegative {
        setId(id);
        this.name = name;
        this.date_of_born = date_of_born;
        this.size = size;
        setWeigth(weigth);
        setHeigth(heigth);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) throws IDNotMatching {
        if(!id.matches("([A-Z]|[a-z]|\\d){4}")){
            throw new IDNotMatching(id);
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDate_of_born() {
        return date_of_born;
    }

    public void setDate_of_born(LocalDate date_of_born) throws NotBornInTheFuture {
        if(LocalDate.now().isBefore(date_of_born)){
            throw new NotBornInTheFuture();
        }
        this.date_of_born = date_of_born;
    }

    public DogSize getSize() {
        return size;
    }

    public void setSize(DogSize size) {
        this.size = size;
    }

    public double getHeigth() {
        return heigth;
    }

    public void setHeigth(double heigth) throws NotZeroOrNegative {
        if(heigth<=0){
            throw new NotZeroOrNegative();
        }
        this.heigth = heigth;
    }

    public double getWeigth() {
        return weigth;
    }

    public void setWeigth(double weigth) throws NotZeroOrNegative {
        if(weigth<=0){
            throw  new NotZeroOrNegative();
        }
        this.weigth = weigth;
    }

    @Override
    public boolean equals(Object obj) {
        Dog objD = (Dog) obj;
        if(this.id == objD.id){
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "ID: "+this.id+"\n"+"Name: "+this.name;
    }
}
