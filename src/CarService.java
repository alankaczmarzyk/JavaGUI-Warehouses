import java.util.*;

abstract class CarService {
    private String nazwa;
    private double powierzchnia;
    public static int IDspot=1;
    private static HashMap<CarService,Set<Vehicle>> listaWszystkichPojazdowWNaprawie = new HashMap<>();
    protected static LinkedHashMap<CarService, Set<Vehicle>> historiaNapraw= new LinkedHashMap<>();
    public static List<CarService> wszystkieMiejsca = new LinkedList<>();
    public boolean czyZajete=false;

    public CarService(String nazwa, double powierzchnia){
        this.nazwa = nazwa;
        this.powierzchnia = powierzchnia;

    }

    public static HashMap<CarService, Set<Vehicle>> getHistoriaNapraw() {
        return historiaNapraw;
    }

    public static List<CarService> getWszystkieMiejsca() {
        return wszystkieMiejsca;
    }

    abstract public void dodajMiejsce();

    abstract void wynajmijMiejsce(Person p,Vehicle vec);

    public abstract int getId();

    @Override
    public String toString() {
        return  "nazwa='" + nazwa + '\'' + " powierzchnia= " +powierzchnia;
    }
}



