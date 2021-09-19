import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

abstract class Warehouse {

    public String nazwa;
    public double powierzchnia;
    public static int idPomieszczenia = 1;
    private double kosztNajmu;
    public double sumaKosztowMagazynu=0;
    public HashMap<Warehouse, Person> Najemcy = new HashMap<>();
    protected double objetosc;
    protected boolean czyZajety=false;
    private LocalDate dataRozpoczeciaNajmu;
    private static LocalDate dataZakonczeniaNajmu;


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


    public double getKosztNajmu(){
        return this.kosztNajmu;
    }

    public abstract void wynajmijMagazyn(Person p);

    public abstract void odbierzUprawnienie(Person p);

    public abstract void dodajUprawnienie(Person p);

    public abstract int getId();

    public LocalDate getDataZakonczenia(){
        return this.dataZakonczeniaNajmu;
    }

    public abstract void osobyUprawnione();

    public abstract HashMap<Warehouse, List<Object>> przedmiotyMagazynu();

    public abstract HashMap<Warehouse, Person> Najemcy();

    public double getObjetosc(){
        return this.objetosc;
    }

    public boolean isCzyZajety() {
        return czyZajety;
    }

    public String toString(){

        return "Magazyn:'"+this.nazwa+"' " + "Powierzchnia: "+this.objetosc+"m^2"+" " ;
    }



}

