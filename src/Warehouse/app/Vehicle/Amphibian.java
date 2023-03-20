package Warehouse.app.Vehicle;

public class Amphibian extends Vehicle{
    private int caterpillarsNumber;
    private int ID;

    public Amphibian(String brand, String type, double engine, int caterpillarsNumber) {
        super(brand, type, engine);
        this.caterpillarsNumber = caterpillarsNumber;
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
