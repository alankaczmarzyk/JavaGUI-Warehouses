package Warehouse.app.CarSpot;

import Warehouse.app.Main;
import Warehouse.app.Menu.MainMenu;
import Warehouse.app.Obj.Person;
import Warehouse.app.Vehicle.Vehicle;
import Warehouse.app.Warehouse.Service;

import java.util.*;

public class CarServiceSpot extends CarService {
    private int id;
    private static int counter = 0;
    private Person[] owner = new Person[1];
    private HashMap<Person, Vehicle> tenantsList = new HashMap<>();
    public Set<Vehicle> currentlyRepairedVehicles = new HashSet<>();
    private static Map<CarServiceSpot, Vehicle> allRepairedVehicles = new HashMap<>();
    private int repairTime;
    private boolean ifCurrentlyRepairs = false;
    private Vehicle thatVehicle;
    public static CarServiceSpot[] carServiceSpots;
    private Queue<Vehicle> vehicleQueue = new LinkedList<>();
    int count = Service.getRepairPlacesNumber();
    private String newName;
    boolean ifRented =false;

    public CarServiceSpot(String nazwa, double powierzchnia) {
        super(nazwa, powierzchnia);
        newName =nazwa;
        id = spotID++;
        addSpot();
    }

    public Queue<Vehicle> getVehicleQueue() {
        return vehicleQueue;
    }

    public void startRepair(Vehicle v) {
        MainMenu.blockViev();
        if (tenantsList.containsValue(v)) {
            if (!ifCurrentlyRepairs) {
                thatVehicle = v;
                int iloscDni = (int) (Math.random() * 5) + 1;
                int cena = (int) (Math.random() * 2000) + 250;
                MainMenu.getTextArea().setText("Naprawa rozpoczeta.\nCzas trwania: " + iloscDni + " dni.\nKoszt " + cena+".00 PLN.");
                repairTime = iloscDni;
                currentlyRepairedVehicles.add(v);
                allRepairedVehicles.put(this,v);
                repairHistory.put(this, currentlyRepairedVehicles);
                ifCurrentlyRepairs = true;
            } else {
                if (vehicleQueue.contains(v)) {
                    MainMenu.getTextArea().setText("Ten pojazd znajduje sie w kolejce oczekujacych na naprawe.");
                } else if (thatVehicle == v) {
                    MainMenu.getTextArea().setText("Ten pojazd jest juz w naprawie.");
                }
            }
        } else {
            MainMenu.getTextArea().setText("Najpierw wynajmij miejsce dla tego pojazdu.");
        }

    }

    public static Map<CarServiceSpot, Vehicle> getAllRepairedVehicles() {
        return allRepairedVehicles;
    }

    public void finishRepair() {
        MainMenu.blockViev();
        if (currentlyRepairedVehicles.contains(thatVehicle)) {
            currentlyRepairedVehicles.remove(thatVehicle);
            allRepairedVehicles.remove(this);
            if (!vehicleQueue.isEmpty()) {
                MainMenu.getTextArea().setText("Zakonczono naprawe: " + thatVehicle + " oraz rozpoczeto naprawe: " + vehicleQueue.element());
                Vehicle thisVehicle = vehicleQueue.poll();
                currentlyRepairedVehicles.add(thisVehicle);
                allRepairedVehicles.put(this,thisVehicle);
            } else {
                MainMenu.getTextArea().setText("Zakonczono naprawe pojazdu:\n "+thatVehicle+".");
                czyZajete = false;
                ifCurrentlyRepairs = false;
            }
        } else {
            MainMenu.getTextArea().setText("Nie ma takiego pojazdu w naprawie");
        }

    }

    @Override
    public void addSpot() {
        if (carServiceSpots == null) {
            carServiceSpots = new CarServiceSpot[count];
            carServiceSpots[counter++] = this;
            carServiceList.add(this);
        } else {
            try {
                carServiceSpots[counter++] = this;
                carServiceList.add(this);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IndexOutOfBoundsException("Przekroczono maksymalna ilosc miejsc naprawczych.");

            }
        }
    }

    public static CarServiceSpot[] getCarServiceSpots() {
        return carServiceSpots;
    }

    @Override
    public void rentSpot(Person p, Vehicle vec) {
        MainMenu.blockViev();
        if (!czyZajete) {
            owner[0] = p;
            tenantsList.put(p, vec);
            czyZajete = true;
            MainMenu.getTextArea().setText("Miejsce naprawcze " + newName + " zostalo wynajete.");
        } else {
            MainMenu.getTextArea().setText("Miejsce naprawcze jest juz zarezerwowane. Zostales dodany do kolejki.");
            vehicleQueue.add(vec);
            tenantsList.put(p, vec);
        }

    }

    public Set<Vehicle> getCurrently(){
        return currentlyRepairedVehicles;
    }


    @Override
    public int getId() {
        return id;
    }

    public boolean czyWlasnieWynajmuje(Person p){
        if(p== owner[0]){
            ifRented =true;
        }else {
            ifRented =false;
        }
        return ifRented;
    }


    @Override
    public String toString() {
        return super.toString() + " ID:" + id + " [Miejsce Naprawcze]" + " stan: " + (czyWlasnieWynajmuje(Main.getPerson())? "Zajety przez Ciebie" :  czyZajete ? "Zajete" : "Wolne");
    }


}


