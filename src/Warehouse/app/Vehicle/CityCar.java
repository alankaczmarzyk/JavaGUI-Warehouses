package Warehouse.app.Vehicle;

public class CityCar extends Vehicle{
    private String transmission;
    private int ID;

    public CityCar(String brand, String type, double engine, String transmission) {
        super(brand, type, engine);
        this.transmission = transmission;
        ID = vehicleID++;
    }

    @Override
   public int getVehicleID() {
        return this.ID;
    }

    public String toString() {
        return super.toString() + this.ID;
    }
}
