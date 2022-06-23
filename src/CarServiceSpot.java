import java.util.*;

public class CarServiceSpot extends CarService {
    private int id;
    private static int counter = 0;
    private Person[] wlasciciel = new Person[1];
    private HashMap<Person, Vehicle> osobyWynajmujace = new HashMap<>();
    private Set<Vehicle> listaPojazdowNaprawianych = new HashSet<>();
    private static Map<CarServiceSpot, Vehicle> listaWszystkichPojazdowNaprawianych = new HashMap<>();
    private int czasNaprawy;
    private boolean czyObecnieNaprawiany = false;
    private Vehicle obecnyPojazd;
    public static CarServiceSpot[] listaMiejscNaprawczych;
    private Queue<Vehicle> kolejkaOczekujacych = new LinkedList<>();
    int count = Service.getLiczbaMiejscNaprawczych();
    private String nowaNazwa;
    boolean czyWynajmuje =false;

    public CarServiceSpot(String nazwa, double powierzchnia) {
        super(nazwa, powierzchnia);
        nowaNazwa=nazwa;
        id = IDspot++;
        dodajMiejsce();
    }

    public Queue<Vehicle> getKolejkaOczekujacych() {
        return kolejkaOczekujacych;
    }

    public void rozpocznijNaprawe(Vehicle v) {

        if (osobyWynajmujace.containsValue(v)) {
            if (!czyObecnieNaprawiany) {
                obecnyPojazd = v;
                int iloscDni = (int) (Math.random() * 5) + 1;
                int cena = (int) (Math.random() * 2000) + 250;
                System.out.println("Naprawa rozpoczeta. Czas trwania: " + iloscDni + " dni, " + "koszt " + cena);
                czasNaprawy = iloscDni;
                listaPojazdowNaprawianych.add(v);
                listaWszystkichPojazdowNaprawianych.put(this,v);
                historiaNapraw.put(this, listaPojazdowNaprawianych);
                czyObecnieNaprawiany = true;
            } else {
                if (kolejkaOczekujacych.contains(v)) {
                    System.out.println("Ten pojazd znajduje sie w kolejce oczekujacych na naprawe.");
                } else if (obecnyPojazd == v) {
                    System.out.println("Ten pojazd jest juz w naprawie.");
                }
            }
        } else {
            System.out.println("Najpierw wynajmij miejsce dla tego pojazdu.");
        }

    }


    public static Map<CarServiceSpot, Vehicle> getListaWszystkichPojazdowNaprawianych() {
        return listaWszystkichPojazdowNaprawianych;
    }

    public void zakonczNaprawe() {
        if (listaPojazdowNaprawianych.contains(obecnyPojazd)) {
            listaPojazdowNaprawianych.remove(obecnyPojazd);
            listaWszystkichPojazdowNaprawianych.remove(this);
            if (!kolejkaOczekujacych.isEmpty()) {
                System.out.println("Zakonczono naprawe: " + obecnyPojazd + " oraz rozpoczeto naprawe: " + kolejkaOczekujacych.element());
                Vehicle thisVehicle = kolejkaOczekujacych.poll();
                listaPojazdowNaprawianych.add(thisVehicle);
                listaWszystkichPojazdowNaprawianych.put(this,thisVehicle);
            } else {
                System.out.println("Zakonczono naprawe.");
                czyZajete = false;
                czyObecnieNaprawiany = false;
            }
        } else {
            System.out.println("Nie ma takiego pojazdu w naprawie");
        }

    }

    @Override
    public void dodajMiejsce() {

        if (listaMiejscNaprawczych == null) {
            listaMiejscNaprawczych = new CarServiceSpot[count];
            listaMiejscNaprawczych[counter++] = this;
            wszystkieMiejsca.add(this);
        } else {
            try {
                listaMiejscNaprawczych[counter++] = this;
                wszystkieMiejsca.add(this);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new IndexOutOfBoundsException("Przekroczono maksymalna ilosc miejsc naprawczych.");

            }
        }
    }

    public static CarServiceSpot[] getListaMiejscNaprawczych() {
        return listaMiejscNaprawczych;
    }

    @Override
    void wynajmijMiejsce(Person p, Vehicle vec) {

        if (!czyZajete) {
            wlasciciel[0] = p;
            osobyWynajmujace.put(p, vec);
            czyZajete = true;
            System.out.println("Miejsce naprawcze " +nowaNazwa+ " zostalo wynajete.");
        } else {
            System.out.println("Miejsce naprawcze jest juz zarezerwowane. Zostales dodany do kolejki.");
            kolejkaOczekujacych.add(vec);
            osobyWynajmujace.put(p, vec);
        }

    }

    @Override
    public int getId() {
        return id;
    }

    public boolean czyWlasnieWynajmuje(Person p){
        if(p==wlasciciel[0]){
            czyWynajmuje =true;
        }else {
            czyWynajmuje =false;
        }
        return czyWynajmuje;
    }


    @Override
    public String toString() {
        return super.toString() + " ID:" + id + " [Miejsce Naprawcze]" + " stan: " + (czyWlasnieWynajmuje(Main.getOsoba())? "Zajety przez Ciebie" :  czyZajete ? "Zajete" : "Wolne");
    }


}


