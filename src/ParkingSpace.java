import java.util.*;

public class ParkingSpace {

    private String nazwa;
    private double powierzchnia;
    public double kosztNajmuMiejscaP;
    private static int parkingID=100;
    private int ID;
    private ParkingSpace miejsce;
    public static List<ParkingSpace> miejscaParkingowe;
    public static HashMap<Person,ParkingSpace> NajemcyMiejscParkingowych = new HashMap<>();
    private Vehicle[] pojazd = new Vehicle[1];
    private int counter=0;
    private int ktoryPojazd=0;
    public boolean czyWynajety=false;
    private boolean czyJestPojazd;
    private boolean czyMaWynajetyMagazyn=false;
    private Person wlasciciel;
    private boolean czyWlasciciel;
    public static HashMap<Warehouse, Person> najemcyMagazynow = Warehouse.getOsobyUprawnione();

    public ParkingSpace(String nazwa, double powierzchnia, double kosztNajmuMiejscaP){

        this.nazwa = nazwa;
        this.powierzchnia = powierzchnia;
        this.kosztNajmuMiejscaP = kosztNajmuMiejscaP;

        ID=parkingID++;

        dodajMiejsca();
    }

    public double getKosztNajmuMiejscaP() {
        return kosztNajmuMiejscaP;
    }

    public void dodajMiejsca(){

        if(miejscaParkingowe ==null){
            miejscaParkingowe = new ArrayList<>();
            miejscaParkingowe.add(this);
        }else {
            miejscaParkingowe.add(this);
        }

    }

    public static List<ParkingSpace> getMiejscaParkingowe() {
        return miejscaParkingowe;
    }


    public void wynajmijMiejsceParkingowe(Person p, Warehouse w, int iloscDni){


        for(Map.Entry<Warehouse, Person> osoba : najemcyMagazynow.entrySet()) {
            Warehouse key = osoba.getKey();
            Person value = osoba.getValue();

            if(key.equals(w) && value.equals(p)){
                czyMaWynajetyMagazyn=true;
            }
        }
        if(czyMaWynajetyMagazyn) {
            if (iloscDni <= 14) {
                if (!czyWynajety) {
                    miejsce = this;
                    NajemcyMiejscParkingowych.put(p, this);
                    czyWynajety = true;
                    wlasciciel = p;
                    System.out.println(wlasciciel+ " wynajal miejsce parkingowe: "+ this.nazwa + this.ID +" w ramach magazynu: " + w.toString() + " na " + iloscDni + " dni");
                } else {
                    System.out.println("To miejsce parkingowe jest juz wynajete.");
                }
            } else {
                System.out.println("Maksymalna ilosc dni to 14.");
            }
        }else {
            System.out.println("Miejsce parkingowe mozna wynajac tylko w ramach wynajecia magazynu.");
        }

    }

    public void dodajPojazd(Person p,Vehicle v){
        if(!czyWynajety){
            System.out.println("Najpierw wynajmij miejsce zeby dodac pojazd");
        }else {
            if (p == wlasciciel) {
                try {
                    pojazd[ktoryPojazd++] = v;
                    czyJestPojazd = true;
                    System.out.println("Pojazd zostal dodany na miejsce parkingowe.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Moze byc tylko jeden pojazd na miejscu parkingowym");
                }
            }else {
                System.out.println("Tylko wlasciciel moze dodac pojazd na miejsce parkingowe.");
            }
        }

    }

    public void usunPojazd(Person p){

        if(pojazd[0]==null){
            System.out.println("Nic nie ma na tym miejscu.");
        }else {
            if (wlasciciel == p) {
                czyJestPojazd = false;
                System.out.println("Pojazd " + pojazd[0] +" zostal usuniety.");
                pojazd[0] = null;
            }else {
                System.out.println("Tylko wlasciciel moze usunac pojazd ze swojego miejsca parkingowego.");
            }
        }
    }

    public int getID() {
        return ID;
    }

    public boolean czyJestWlascicielemMagazynu(Person p){
        if(p==wlasciciel){
        czyWlasciciel=true;
        }else {
            czyWlasciciel=false;
        }
        return czyWlasciciel;
    }

    public void osobaWynajmujaca(){
        System.out.println(NajemcyMiejscParkingowych);
    }
    @Override
    public String toString() {
        return "Miejsce Parkingowe{" +
                "nazwa='" + nazwa + '\'' +
                ",Stan=" +(czyJestWlascicielemMagazynu(Main.getOsoba())? "wynajety przez Ciebie ": (!czyWynajety? "dostepny do wynajecia, ":"zajety, "))+
                (czyJestPojazd?"zaparkowano: "+ pojazd[0] : "puste miejsce ")+
                ", Parking ID:=" + ID +
                '}';
    }
}
