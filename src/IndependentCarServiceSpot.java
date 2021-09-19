import java.sql.SQLOutput;
import java.util.*;

public class IndependentCarServiceSpot extends CarServiceSpot {


    private int ID;
    private boolean czyWolny;
    private static int IDserwisu=1;
    private int counter=0;
    private Person[] wlasciciel = new Person[1];
    private Vehicle[] miejscaSerwisowe= new Vehicle[1];
    private Vehicle[] miejscaNaprawcze = new Vehicle[3];
    public static HashMap<Person, IndependentCarServiceSpot>  osobyWynajmujace= new HashMap<>();
    private Set<IndependentCarServiceSpot> listaMiejscSerwisowych = new HashSet<>();
    private Set<Person> listaOczekujacych = new HashSet<>();
    private  Set<Vehicle> listaPojazdowNaprawianych = new HashSet<>();
    private HashMap<IndependentCarServiceSpot, Set<Vehicle>> listaNaprawianych= new HashMap<>();
    private HashMap<IndependentCarServiceSpot, List<Vehicle>> kolejkaOczekujacych= new HashMap<>();
    private  List<Vehicle> listaPojazdowOczekujacych = new LinkedList<>();
    protected boolean czyZajety;
    private Warehouse w1;


    public IndependentCarServiceSpot(String nazwa, double powierzchnia) {
        super(nazwa, powierzchnia);

        ID = IDserwisu++;
        listaMiejscSerwisowych.add(this);


    }


    public int getID() {
        return this.ID;
    }

    @Override
    public void wykonajNaprawe(Vehicle v, double Cena) {
        int iloscDni = (int)(Math.random()*5)+1;
        listaPojazdowNaprawianych.add(v);
        System.out.println("Naprawa rozpoczeta. Czas trwania: " + iloscDni + " dni, " + "koszt " + Cena);
        listaPojazdowNaprawianych.add(v);
        listaNaprawianych.put(this,listaPojazdowNaprawianych);
        historiaNapraw.put(this,listaPojazdowNaprawianych);
    }

    @Override
    public void zakonczNaprawe(Vehicle v) {
        for (int i = 0; i < miejscaNaprawcze.length; i++) {
            if(miejscaNaprawcze[i]==v)
                miejscaNaprawcze[i]=null;
            listaPojazdowNaprawianych.remove(v);
        }
        System.out.println("Naprawa zakonczona");
    }

    public void wynajmijMiejscSerwisowe(Person p, int iloscDni){

            try {
                wlasciciel[0] = p;
                osobyWynajmujace.put(p, this);
                czyZajety = true;
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Miejsce jest juz wynajete.");
            }
        System.out.println("Miejsce serwisowe zostalo wynajete.");
    }


    @Override
    void zglosPotrzebeSerwisowa(Person p, Vehicle vec, boolean czyNaprawaSamodzielna) {

        if (!czyNaprawaSamodzielna) {
            try {
                miejscaNaprawcze[counter++] = vec;
                wykonajNaprawe(vec,(int)(Math.random()*500)+100);

            }catch (IndexOutOfBoundsException e){
                listaOczekujacych.add(p);
                listaPojazdowOczekujacych.add(vec);
                ParkingSpace p1 = new ParkingSpace("tymczasowy",20,0);
                p1.wynajmijMiejsceParkingowe(p,null,13);
                p1.dodajPojazd(vec);
                kolejkaOczekujacych.put(this,listaPojazdowOczekujacych);
            }

        } else {
            if(wlasciciel[0]==p){
                miejscaSerwisowe[0] = vec;
                wykonajNapraweSamodzielna(p,vec);
            }else {
                System.out.println("Najpierw nalezy wynajac miejsce serwisowe.");
            }

        }
    }

    public void wykonajNapraweSamodzielna(Person p, Vehicle v){

        if(p!=wlasciciel[0]){
            System.out.println("Tylko wlasciciel moze rozpoczac prace przy pojezdzie");
        }else {
            wykonajNaprawe(v,0);
        }
    }

    public void sprawdzStan(){

        if(miejscaNaprawcze[0]!=null) {
            for (int i = 0; i < miejscaNaprawcze.length; i++) {
                if (miejscaNaprawcze[i] == null) {
                    miejscaNaprawcze[i] = listaPojazdowOczekujacych.get(0);
                    listaPojazdowOczekujacych.remove(0);
                }

            }
        }
    }

    public  HashMap<IndependentCarServiceSpot, Set<Vehicle>> getHistoriaNapraw() {
        return historiaNapraw;
    }

    public Set<Vehicle> getListaPojazdowNaprawianych() {
        return listaPojazdowNaprawianych;
    }

    public HashMap<IndependentCarServiceSpot, Set<Vehicle>> getListaNaprawianych() {
        return listaNaprawianych;
    }

    public void osobyWynajmujaca(){
        System.out.println(osobyWynajmujace);
    }

    public HashMap<IndependentCarServiceSpot, List<Vehicle>> getKolejkaOczekujacych() {
        return kolejkaOczekujacych;
    }

    public List<Vehicle> getListaPojazdowOczekujacych() {
        return listaPojazdowOczekujacych;
    }

    public String toString(){
        return super.toString() + " ID:"+this.ID + "";
    }



}


