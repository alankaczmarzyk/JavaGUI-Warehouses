import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

abstract class Warehouse {
    private String name;
    private double space;
    public static int warehouseID = 1;
    private double rentalCost;
    public static HashMap<Warehouse, Person> authorizedPeople = new HashMap<>();
    protected double volume;
    protected boolean ifBusy =false;
    private LocalDate startDateOfLease;
    private LocalDate finishDateOfLease;
    public static List<Warehouse> warehousesList = new LinkedList<>();
    public static Map<Person, List<Warehouse>> tenantsList = new HashMap<>();
    public boolean ifConsumer;

    public Warehouse(String name, double volume, double rentalCost, LocalDate startDateOfLease, LocalDate finishDateOfLease){
        this.name = name;
        this.rentalCost = rentalCost;
        this.volume = volume;
        this.startDateOfLease = startDateOfLease;
        this.finishDateOfLease = finishDateOfLease;
    }

    public Warehouse(String name, double dlugosc, double szerokosc, double wysokosc, double rentalCost, LocalDate startDateOfLease, LocalDate finishDateOfLease){
        this.name = name;
        this.rentalCost = rentalCost;
        this.volume =dlugosc*szerokosc*wysokosc;

    }

    public abstract void addItem(Person p, Object o) throws TooManyThingsException;

    public abstract void takeOutItem(Person p, Object o);

    public static List<Warehouse> getWarehousesList() {
        return warehousesList;
    }

    public double getRentalCost(){
        return this.rentalCost;
    }

    public abstract void addWarehouse();

    public abstract void rentWarehouse(Person p, ParkingSpace ps);

    public abstract void removePermission(Person p);

    public abstract void addPermission(Person p);

    public abstract int getId();

    public LocalDate getFinishDateOfLease(){
        return finishDateOfLease;
    }

    public abstract HashMap<Warehouse, List<Object>> getWarehouseItems();

    public abstract HashMap<Warehouse, Person> getTenants();

    public static HashMap<Warehouse, Person> getAuthorizedPeople() {
        return authorizedPeople;
    }

    public double getVolume(){
        return this.volume;
    }

    public static Map<Person, List<Warehouse>> getTenantsList() {
        return tenantsList;
    }

    public boolean isIfBusy() {
        return ifBusy;
    }

    public String toString(){
        MainMenu.getTextArea().setText("Magazyn:'"+this.name +"' " + "Powierzchnia: "+this.volume +"m^2"+" ");
        return "Magazyn:'"+this.name +"' " + "Powierzchnia: "+this.volume +"m^2"+" " ;
    }



}

