import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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

    abstract int getVehicleID();

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
