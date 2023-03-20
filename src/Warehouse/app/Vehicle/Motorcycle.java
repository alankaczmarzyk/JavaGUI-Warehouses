package Warehouse.app.Vehicle;

public class Motorcycle extends Vehicle{
    private int horseower;
    private int ID;

    public Motorcycle(String brand, String type, double engine, int horseower) {
        super(brand, type, engine);
        this.horseower = horseower;
        ID= vehicleID++;
    }

    @Override
    public int getVehicleID() {
        return this.ID;
    }

    public String toString() {
        return super.toString() + this.ID;
    }
}
