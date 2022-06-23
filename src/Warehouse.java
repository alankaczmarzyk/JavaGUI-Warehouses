import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

abstract class Warehouse {

    public String nazwa;
    public double powierzchnia;
    public static int idPomieszczenia = 1;
    private double kosztNajmu;
    public static HashMap<Warehouse, Person> osobyUprawnione = new HashMap<>();
    protected double objetosc;
    protected boolean czyZajety=false;
    private LocalDate dataRozpoczeciaNajmu;
    private LocalDate dataZakonczeniaNajmu;
    public static List<Warehouse> wszystkieMagazyny = new LinkedList<>();
    public static Map<Person, List<Warehouse>> osobyWynajmujace = new HashMap<>();
    public boolean czyKonsumecki;

    public Warehouse(String nazwa, double objetosc, double kosztNajmu, LocalDate dataRozpoczeciaNajmu, LocalDate dataZakonczeniaNajmu){

        this.nazwa = nazwa;
        this.kosztNajmu = kosztNajmu;
        this.objetosc=objetosc;
        this.dataRozpoczeciaNajmu=dataRozpoczeciaNajmu;
        this.dataZakonczeniaNajmu=dataZakonczeniaNajmu;
    }
    public abstract void wlozPrzedmiot(Person p,Object o) throws TooManyThingsException;

    public abstract void wyjmijPrzedmiot(Person p,Object o);

    public Warehouse(String nazwa, double dlugosc, double szerokosc, double wysokosc, double kosztNajmu, LocalDate dataRozpoczeciaNajmu, LocalDate dataZakonczeniaNajmu){

        this.nazwa = nazwa;
        this.kosztNajmu = kosztNajmu;
        this.objetosc=dlugosc*szerokosc*wysokosc;


    }

    public static List<Warehouse> getWszystkieMagazyny() {
        return wszystkieMagazyny;
    }

    public double getKosztNajmu(){
        return this.kosztNajmu;
    }

    public abstract void dodajMagazyn();

    public abstract void wynajmijMagazyn(Person p, ParkingSpace ps);

    public abstract void odbierzUprawnienie(Person p);

    public abstract void dodajUprawnienie(Person p);

    public abstract int getId();

    public LocalDate getDataZakonczenia(){
        return dataZakonczeniaNajmu;
    }

    public abstract void osobyUprawnione();

    public abstract HashMap<Warehouse, List<Object>> przedmiotyMagazynu();

    public abstract HashMap<Warehouse, Person> Najemcy();

    public static HashMap<Warehouse, Person> getOsobyUprawnione() {
        return osobyUprawnione;
    }

    public double getObjetosc(){
        return this.objetosc;
    }

    public static Map<Person, List<Warehouse>> getOsobyWynajmujace() {
        return osobyWynajmujace;
    }

    public boolean isCzyZajety() {
        return czyZajety;
    }

    public String toString(){

        return "Magazyn:'"+this.nazwa+"' " + "Powierzchnia: "+this.objetosc+"m^2"+" " ;
    }



}

