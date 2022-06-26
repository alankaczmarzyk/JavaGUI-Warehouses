public class OffRoadCar extends Vehicle{
    private String drive;
    private int ID;

    public OffRoadCar(String brand, String type, double engine, String drive) {
        super(brand, type, engine);
        this.drive = drive;
        ID = vehicleID++;
    }

    @Override
    int getVehicleID() {
        return this.ID;
    }

    @Override
    public String toString() {
        return super.toString() + this.ID;
    }
}
