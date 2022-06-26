import java.util.HashSet;
import java.util.Set;

abstract public class Vehicle {
    private String brand;
    private String type;
    private double engine;
    public static int vehicleID =1;
    public static Set<Vehicle> vehiclesList = new HashSet<>();

    public Vehicle(String brand, String type, double engine){
        this.brand = brand;
        this.type = type;
        this.engine = engine;

        vehiclesList.add(this);
    }

    abstract int getVehicleID();

    public static Set<Vehicle> getVehiclesList() {
        return vehiclesList;
    }

    @Override
    public String toString() {
        return "Pojazd: " +
                "marka='" + brand + '\'' +
                ", ID=";

    }
}
