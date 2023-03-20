package Warehouse.app.CarSpot;

import Warehouse.app.Obj.Person;
import Warehouse.app.Vehicle.Vehicle;

import java.util.*;

public abstract class CarService {
    private String name;
    private double space;
    public static int spotID =1;
    protected static LinkedHashMap<CarService, Set<Vehicle>> repairHistory = new LinkedHashMap<>();
    public static List<CarService> carServiceList = new LinkedList<>();
    public boolean czyZajete=false;

    public CarService(String name, double space){
        this.name = name;
        this.space = space;

    }

    public static HashMap<CarService, Set<Vehicle>> getRepairHistory() {
        return repairHistory;
    }

    public static List<CarService> getCarServiceList() {
        return carServiceList;
    }

    abstract public void addSpot();

    public abstract void rentSpot(Person p, Vehicle vec);

    public abstract int getId();

    @Override
    public String toString() {
        return  "nazwa='" + name + '\'' + " powierzchnia= " + space;
    }
}



