import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConsumerWarehouse extends Warehouse {
    private int ID;
    private int counter = 0;
    private static int counter2 = 0;
    private Person[] owner = new Person[1];
    public List<Person> peopleList = new ArrayList<>();
    public List<Object> objectsList = new ArrayList<>();
    private double warehouseCost;
    private double parkingCost;
    public HashMap<Warehouse, List<Person>> consumerWarehouseTenants = new HashMap<>();
    private HashMap<Warehouse, List<Object>> objectsInWarehouses = new HashMap<>();
    private static double volumeSumOfItems = 0;
    private static ConsumerWarehouse[] consumerWarehouseList;


    public ConsumerWarehouse(String name, double volume, double rentalCost, LocalDate startDateOfLease, LocalDate finishDateOfLease) {
        super(name, volume, rentalCost, startDateOfLease, finishDateOfLease);
        ID = warehouseID++;
        warehouseCost = rentalCost;
        addWarehouse();
    }

    public ConsumerWarehouse(String name, double length, double szerokosc, double width, double rentalCost, LocalDate startDateOfLease, LocalDate finishDateOfLease) {
        super(name, length, szerokosc, width, rentalCost, startDateOfLease, finishDateOfLease);
        ID = warehouseID++;
        warehouseCost = rentalCost;
        addWarehouse();
        ifConsumer = true;
    }

    @Override
    public void addWarehouse() {
        int count = Service.getRepairPlaces();

        if (consumerWarehouseList == null) {
            consumerWarehouseList = new ConsumerWarehouse[count];
            consumerWarehouseList[counter2++] = this;
            Warehouse.warehousesList.add(this);
        } else {
            try {
                consumerWarehouseList[counter2++] = this;
                Warehouse.warehousesList.add(this);
            } catch (IndexOutOfBoundsException e) {
                throw new IndexOutOfBoundsException("Przekroczono maksymalna ilosc magazynow konsumenckich.");

            }
        }
    }

    public static ConsumerWarehouse[] getConsumerWarehouseList() {
        return consumerWarehouseList;
    }

    @Override
    public int getId() {
        return ID;
    }

    @Override
    public void rentWarehouse(Person wl, ParkingSpace ps) {
        try {
            owner[counter++] = wl;
            System.out.println(wl + " wynajmuje " + toString());
            //MainMenu.getTextArea().append(wl + " wynajmuje " + toString());
            peopleList.add(wl);
            authorizedPeople.put(this, wl);
            tenantsList.computeIfAbsent(wl, k -> new ArrayList<>()).add(this);
            consumerWarehouseTenants.put(this, peopleList);
            ifBusy = true;
            wl.addWarehouse(this);

            if (ps == null) {
                parkingCost = 0;
            } else {
                parkingCost = ps.getRentalCost();
                if (parkingCost + warehouseCost > 1250) {
                    System.out.println("Suma kosztow najmu przekracza 1250 zl.");
                    MainMenu.getTextArea().setText("Suma kosztow najmu przekracza 1250 zl.");
                } else {
                    ps.rentParkingSpace(wl, this, 14);
                }
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ten magazyn jest juz wynajety.");
            MainMenu.getTextArea().setText("Ten magazyn jest juz wynajety.");
        }
    }

    @Override
    public void addPermission(Person p) {
        if (peopleList.contains(p)) {
            System.out.println("Ta osoba ma juz uprawnienie!");
        } else {
            peopleList.add(p);
            consumerWarehouseTenants.put(this, peopleList);
            authorizedPeople.put(this, p);
            System.out.println("Dodano uprawnienie.");
        }


    }


    @Override
    public void removePermission(Person p) {
        if (p == peopleList.get(0)) {
            System.out.println("Nie mozesz zabrac uprawnienia wlascicielowi!");
        } else {
            peopleList.remove(p);
            consumerWarehouseTenants.put(this, peopleList);
            authorizedPeople.remove(this);
            System.out.println("Zabrano uprawnienie");
        }
    }


    @Override
    public void addItem(Person p, Object o) throws TooManyThingsException {
        volumeSumOfItems += o.getArea();
        if (this.getVolume() >= volumeSumOfItems) {
            if (peopleList.contains(p)) {
                objectsList.add(o);
                objectsInWarehouses.put(this, objectsList);
                System.out.println("Dodales przedmiot do magazynu.");
            } else {
                System.out.println("Nie masz uprawnien do wlozenia przedmiotu.");
            }
        } else {
            throw new TooManyThingsException("Remove some old items to insert a new item.");
        }

    }

    @Override
    public void takeOutItem(Person p, Object o) {
        if (peopleList.contains(p)) {
            if (objectsList.contains(o)) {
                objectsList.remove(o);
                objectsInWarehouses.put(this, objectsList);
                System.out.println("Wyjales przedmiot z magazynu.");
            } else {
                System.out.println("Nie ma takiego obiektu w magazynie.");
            }
        } else {
            System.out.println("Nie masz uprawnien do wyjmowania przedmiotu.");
        }
    }

    @Override
    public HashMap<Warehouse, List<Object>> getWarehouseItems() {
        return objectsInWarehouses;
    }

    @Override
    public HashMap<Warehouse, Person> getTenants() {
        return authorizedPeople;
    }

    @Override
    public String toString() {
        return super.toString() + "Id:" + this.ID + " [Konsumecki]";

    }

}
