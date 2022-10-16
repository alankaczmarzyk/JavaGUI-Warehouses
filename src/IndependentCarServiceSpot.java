import java.util.*;

public class IndependentCarServiceSpot extends CarService{
    private int id;
    private static int counter=0;
    private Person[] owner = new Person[1];
    private static IndependentCarServiceSpot[] independentCarServiceSpots;
    private Set<Vehicle> servicedVehiclesList = new HashSet<>();
    private  HashMap<Person, Vehicle> peopleAndVehicles = new HashMap<>();
    private static Map<IndependentCarServiceSpot, Vehicle> allServicedVehiclesList = new HashMap<>();
    private  Queue<Vehicle> queueOfWaitingVehicles = new LinkedList<>();;
    private Queue<Person> ownersQueue = new LinkedList<>();
    private boolean ifCurrentlyServiced=false;
    private Vehicle thatVehicle;
    private boolean ifOwner=false;
    private boolean canStartService =false;
    private String newName;
    private boolean ifRented =false;

    public IndependentCarServiceSpot(String nazwa, double powierzchnia) {
        super(nazwa, powierzchnia);
        newName =nazwa;
        id = spotID++;
        addSpot();
    }

    public Queue<Vehicle> getQueueOfWaitingVehicles() {
        return queueOfWaitingVehicles;
    }

    public void startSelfRepair(Person p, Vehicle v) {
            if(peopleAndVehicles.containsValue(v)) {
                Person osoba = p;
                Vehicle pojazd = v;
                for(Map.Entry<Person, Vehicle> os : peopleAndVehicles.entrySet()){
                    if(os.getKey().equals(p) && os.getValue().equals(v)){
                        ifOwner =true;
                    }
                }
                if(!ifOwner){
                    MainMenu.getTextArea().setText("Tylko wlasciciel moze rozpoczac prace serwisowa przy pojezdzie.");
                }else {
                    if (!ifCurrentlyServiced) {
                        thatVehicle = v;
                        servicedVehiclesList.add(v);
                        allServicedVehiclesList.put(this, v);
                        repairHistory.put(this, servicedVehiclesList);
                        MainMenu.getTextArea().setText("Wlasciciel pojazdu rozpoczal serwisowanie.");
                        ifCurrentlyServiced = true;
                    } else {
                        if (queueOfWaitingVehicles.contains(v)) {
                            canStartService =true;
                            MainMenu.getTextArea().setText("Ten pojazd znajduje sie w kolejce oczekujacych na serwis.");

                        } else if (thatVehicle == v) {
                            MainMenu.getTextArea().setText("Ten pojazd jest juz w serwisie.");
                        } else {
                        }

                    }
                    ifOwner =false;
                }
            }else {
                System.out.println("Najpierw wynajmij miejsce dla tego pojazdu.");
            }
    }

    public void finishSelfRepair(Person p) {

        if(p== owner[0]) {
            if (servicedVehiclesList.contains(thatVehicle)) {
                servicedVehiclesList.remove(thatVehicle);
               allServicedVehiclesList.remove(this);
                if(canStartService) {
                    MainMenu.getTextArea().setText("Wlasciciel zakonczyl naprawe serwisowa: " + thatVehicle + " Jego miejsce zastapil: " + queueOfWaitingVehicles.element());
                    Vehicle thisVehicle = queueOfWaitingVehicles.poll();
                    servicedVehiclesList.add(thisVehicle);
                    allServicedVehiclesList.put(this,thisVehicle);
                    owner[0] = ownersQueue.poll();
                }else {
                    MainMenu.getTextArea().setText("Wlasciciel zakonczyl naprawe serwisowa.");
                    czyZajete=false;
                    ifCurrentlyServiced =false;
                }
            }
        }else {
            MainMenu.getTextArea().setText("Tylko wlasciciel moze zakonczyc swoja prace.");
        }
    }



    @Override
    public void addSpot() {
        int count = Service.getServiceLocationsNumber();
        if (independentCarServiceSpots == null) {
            independentCarServiceSpots = new IndependentCarServiceSpot[count];
            independentCarServiceSpots[counter++] = this;
            carServiceList.add(this);
        } else {
            try {
                independentCarServiceSpots[counter++] = this;
                carServiceList.add(this);
            }catch (ArrayIndexOutOfBoundsException e) {
                throw new IndexOutOfBoundsException("Przekroczono maksymalna ilosc miejsc serwisowych.");

            }
        }

    }

    public static IndependentCarServiceSpot[] getIndependentCarServiceSpots() {
        return independentCarServiceSpots;
    }

    @Override
    void rentSpot(Person p, Vehicle vec) {
        if(!czyZajete){
            owner[0] = p;
            peopleAndVehicles.put(p, vec);
            czyZajete = true;
            MainMenu.getTextArea().setText("Miejsce serwisowe "+ newName +" zostalo wynajete.");
        } else{
            MainMenu.getTextArea().setText("Miejsce serwisowe jest juz zarezerwowane. Czekasz w kolejce.");
            queueOfWaitingVehicles.add(vec);
            peopleAndVehicles.put(p,vec);
        }
    }

    public boolean ifCurrentlyRentsSpot(Person p){
        if(p== owner[0]){
            ifRented =true;
        }else {
            ifRented =false;
        }
        return ifRented;
    }

    @Override
    public int getId() {
        return id;
    }

    public static Map<IndependentCarServiceSpot, Vehicle> getAllServicedVehiclesList() {
        return allServicedVehiclesList;
    }


    @Override
    public String toString(){
        return super.toString()+ " [Miejsce Serwisowe]" + " ID:"+ id + " stan: "+(ifCurrentlyRentsSpot(Main.getPerson())? "Wynajety przez Ciebie": czyZajete? "Zajete": "Wolne");
    }

}
