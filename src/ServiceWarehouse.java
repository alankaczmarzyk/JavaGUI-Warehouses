import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServiceWarehouse extends Warehouse {
    private int ID;
    private int counter=0;
    private static int counter2=0;
    private Person[] owner = new Person[1];
    public  List<Person> peopleSW = new ArrayList<>();
    public  List<Object> objectsSW = new ArrayList<>();
    private double warehouseCost;
    private double parkingCost;
    public static HashMap<ServiceWarehouse, List<Person>> serviceWarehouseTenants = new HashMap<>();
    private HashMap<Warehouse, List<Object>> objectsList = new HashMap<>();
    private static double volumeSumOfItems =0;
    private static ServiceWarehouse[] servicePremisesList;

    public ServiceWarehouse(String name, double volume, double rentalCost, LocalDate startDateOfLease, LocalDate finishDateOfLease) {
        super(name, volume, rentalCost, startDateOfLease, finishDateOfLease);
        ID= warehouseID++;
        warehouseCost =rentalCost;
        addWarehouse();
        ifConsumer =false;

    }

    public ServiceWarehouse(String name, double length, double width, double height, double rentalCost, LocalDate startDateOfLease, LocalDate finishDateOfLease ) {
        super(name, length, width, height, rentalCost, startDateOfLease, finishDateOfLease);
        ID= warehouseID++;
        warehouseCost =rentalCost;
        addWarehouse();
    }

    @Override
    public void addWarehouse() {
        int count = Service.getServiceLocations();

        if (servicePremisesList == null) {
            servicePremisesList = new ServiceWarehouse[count];
            servicePremisesList[counter2++] = this;
            Warehouse.warehousesList.add(this);

        } else {
            try {
                servicePremisesList[counter2++] = this;
                Warehouse.warehousesList.add(this);
            } catch (IndexOutOfBoundsException e) {
                throw new IndexOutOfBoundsException("Przekroczono maksymalna ilosc magazynow serwisowych.");

            }

        }
    }

    public static ServiceWarehouse[] getServicePremisesList() {
        return servicePremisesList;
    }

    @Override
    public int getId() {
        return ID;
    }

    @Override
    public void rentWarehouse(Person wl, ParkingSpace ps) {
        try {
                owner[counter++] = wl;
                System.out.println(wl + " wynajmuje " + this +"\n"+"\n");
                MainMenu.getTextArea().setText(wl + " wynajmuje " + toString());
                peopleSW.add(wl);
                authorizedPeople.put(this,wl);
                tenantsList.computeIfAbsent(wl, k -> new ArrayList<>()).add(this);
                serviceWarehouseTenants.put(this, peopleSW);
                ifBusy =true;
                wl.addWarehouse(this);

            if(ps==null){
                parkingCost =0;
            }else {
                parkingCost = ps.getRentalCost();

                if(parkingCost + warehouseCost >1250){
                    System.out.println("Suma kosztow najmu przekracza 1250 zl.");
                    MainMenu.getTextArea().setText("Suma kosztow najmu przekracza 1250 zl.");
                }else {
                    ps.rentParkingSpace(wl,this,14);

                }
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ten magazyn jest juz wynajety.");
            MainMenu.getTextArea().setText("Ten magazyn jest juz wynajety.");
        }
    }

    @Override
    public void addPermission(Person p) {

        if(peopleSW.contains(p)){
            System.out.println("Ta osoba ma juz uprawnienie!");
        }else {
            peopleSW.add(p);
            authorizedPeople.put(this,p);
            serviceWarehouseTenants.put(this, peopleSW);
            System.out.println("Dodano uprawnienie.");
        }
    }

    @Override
    public void removePermission(Person p) {
        if (p == peopleSW.get(0)) {
            System.out.println("Nie mozesz zabrac uprawnienia wlascicielowi!");
        } else {
            peopleSW.remove(p);
            authorizedPeople.remove(this);
            serviceWarehouseTenants.put(this, peopleSW);
        }
    }

    @Override
    public void addItem(Person p, Object o) throws TooManyThingsException {
        volumeSumOfItems +=o.getArea();
        if(this.getVolume()> volumeSumOfItems) {
            if (peopleSW.contains(p)) {
                objectsSW.add(o);
                objectsList.put(this, objectsSW);
                System.out.println("Dodales przedmiot do magazynu.");
            } else {
                System.out.println("Nie masz uprawnien do wlozenia przedmiotu.");
            }
        }else{
            throw new TooManyThingsException("Remove some old itemvs to insert a new item.");
        }

    }

    @Override
    public void takeOutItem(Person p, Object o) {
        if(peopleSW.contains(p)){
            if(objectsSW.contains(o)) {
                objectsSW.remove(o);
                objectsList.put(this, objectsSW);
                System.out.println("Wyjales przedmiot z magazynu.");
            }else {
                System.out.println("Nie ma takiego obiektu w magazynie.");
            }
        }else {
            System.out.println("Nie masz uprawnien do wyjmowania przedmiotu.");
        }
    }

    @Override
    public HashMap<Warehouse, List<Object>> getWarehouseItems() {
        return objectsList;
    }

    @Override
    public HashMap<Warehouse, Person> getTenants() {
        return authorizedPeople;
    }

    @Override
    public String toString(){
        return super.toString() + "Id:"+ this.ID + " [Serwisowy]";
    }
}
