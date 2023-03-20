package Warehouse.app.Warehouse;

import Warehouse.app.Exception.TooManyThingsException;
import Warehouse.app.Main;
import Warehouse.app.Menu.MainMenu;
import Warehouse.app.Obj.Objects;
import Warehouse.app.Obj.ParkingSpace;
import Warehouse.app.Obj.Person;

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
    public List<Objects> objectsList = new ArrayList<>();
    private double warehouseCost;
    private double parkingCost;
    public HashMap<Warehouse, List<Person>> consumerWarehouseTenants = new HashMap<>();
    private HashMap<Warehouse, List<Objects>> objectsInWarehouses = new HashMap<>();
    private static double volumeSumOfItems = 0;
    private static ConsumerWarehouse[] consumerWarehouseList;


    public ConsumerWarehouse(String name, double volume, double rentalCost, LocalDate startDateOfLease, LocalDate finishDateOfLease) {
        super(name, volume, rentalCost, startDateOfLease, finishDateOfLease);
        ID = Warehouse.warehouseID++;
        warehouseCost = rentalCost;
        addWarehouse();
    }

    public ConsumerWarehouse(String name, double length, double szerokosc, double width, double rentalCost, LocalDate startDateOfLease, LocalDate finishDateOfLease) {
        super(name, length, szerokosc, width, rentalCost, startDateOfLease, finishDateOfLease);
        ID = Warehouse.warehouseID++;
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
            System.out.println(wl + " wynajmuje " + this);
            MainMenu.getTextArea().setText(wl + " wynajmuje " + this +"\n"+"\n");
            peopleList.add(wl);
            Warehouse.authorizedPeople.put(this, wl);
            Warehouse.tenantsList.computeIfAbsent(wl, k -> new ArrayList<>()).add(this);
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
            MainMenu.blockViev();
            MainMenu.getTextArea().setText("Ta osoba ma juz uprawnienie!");
        } else {
            peopleList.add(p);
            consumerWarehouseTenants.put(this, peopleList);
            Warehouse.authorizedPeople.put(this, p);
            MainMenu.blockViev();
            MainMenu.getTextArea().setText("Dodano uprawnienie do magazynu: "+ Main.warehouse);
        }


    }


    @Override
    public void removePermission(Person p) {
        if (p == peopleList.get(0)) {
            System.out.println("Nie mozesz zabrac uprawnienia wlascicielowi!");
        } else {
            peopleList.remove(p);
            consumerWarehouseTenants.put(this, peopleList);
            Warehouse.authorizedPeople.remove(this);
            System.out.println("Zabrano uprawnienie");
        }
    }


    @Override
    public void addItem(Person p, Objects o) throws TooManyThingsException {
        volumeSumOfItems += o.getArea();
        if (this.getVolume() >= volumeSumOfItems) {
            if (peopleList.contains(p)) {
                objectsList.add(o);
                objectsInWarehouses.put(this, objectsList);
                MainMenu.getTextArea().setText("Dodales przedmiot: \n"+o+"\n do magazynu \n"+ Main.warehouse);
            } else {
                MainMenu.getTextArea().setText("Nie masz uprawnien do wlozenia przedmiotu.");
            }
        } else {
            throw new TooManyThingsException("Remove some old items to insert a new item.");
        }
    }

    @Override
    public void takeOutItem(Person p, Objects o) {
        MainMenu.blockViev();
        if (peopleList.contains(p)) {
            if (objectsList.contains(o)) {
                objectsList.remove(o);
                objectsInWarehouses.put(this, objectsList);
                MainMenu.getTextArea().setText("Wyjales przedmiot z magazynu.");
            } else {
                MainMenu.getTextArea().setText("Nie ma takiego obiektu w magazynie.");
            }
        } else {
            MainMenu.getTextArea().setText("Nie masz uprawnien do wyjmowania przedmiotu.");
        }
    }

    @Override
    public HashMap<Warehouse, List<Objects>> getWarehouseItems() {
        return objectsInWarehouses;
    }

    @Override
    public HashMap<Warehouse, Person> getTenants() {
        return Warehouse.authorizedPeople;
    }

    @Override
    public String toString() {
        return super.toString() + "Id:" + this.ID + " [Konsumecki]";

    }

}
