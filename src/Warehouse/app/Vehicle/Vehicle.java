package Warehouse.app.Vehicle;

import java.util.LinkedList;
import java.util.List;

abstract public class Vehicle {
    private String brand;
    private String type;
    private double engine;
    public static int vehicleID =1;
    public static List<Vehicle> vehiclesList = new LinkedList<>();

    public Vehicle(String brand, String type, double engine){
        this.brand = brand;
        this.type = type;
        this.engine = engine;

        vehiclesList.add(this);
    }

    public abstract int getVehicleID();

    public static List<Vehicle> getVehiclesList() {
        return vehiclesList;
    }

    @Override
    public String toString() {
        return "Pojazd: " +
                "marka='" + brand + '\'' +
                ", ID=";

    }
}
