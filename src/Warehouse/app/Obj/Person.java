package Warehouse.app.Obj;

import Warehouse.app.Warehouse.Warehouse;
import Warehouse.app.Exception.NeverRentException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Person {
    public String firstName;
    public String lastName;
    private int PESEL;
    private String address;
    private LocalDate firstLease;
    private int id;
    private static int personID =1;
    private List<Warehouse> rentedWarehouses = new ArrayList<>();
    private static List<Person> personList;

    public Person(String firstName, String lastName, int PESEL, String address, LocalDate firstLease) throws NeverRentException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.PESEL=PESEL;
        this.address = address;
        this.firstLease = firstLease;
        id = personID++;
        addPeople();
    }

    public void addPeople(){
        if(personList ==null){
            personList = new LinkedList<>();
            personList.add(this);
        }else {
            personList.add(this);
        }

    }

    public static List<Person> getPersonList(){
        return personList;
    }

    public List<Warehouse> getRentedWarehousesList() {
        return rentedWarehouses;
    }

    public int getId() {
        return id;
    }

    public void addWarehouse(Warehouse w){
        rentedWarehouses.add(w);
    }

    public String toString(){
        return "Osoba: "+this.firstName + " " + this.lastName + " ID: " + this.id;
    }





}
