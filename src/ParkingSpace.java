import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ParkingSpace {

    private String nazwa;
    private double powierzchnia;
    private double kosztNajmuMiejscaP;
    private static int parkingID=100;
    private int ID;
    private ParkingSpace[] iloscMiejsc = new ParkingSpace[1];
    public List<ParkingSpace> miejscaPS = new ArrayList<>();
    public static HashMap<Person,ParkingSpace> NajemcyMiejscParkingowych = new HashMap<>();
    private Vehicle[] pojazd = new Vehicle[1];
    private int counter=0;
    private int ktoryPojazd=0;
    private boolean czyWynajety;
    private boolean czyJestPojazd;
    private Person osoba;

    public ParkingSpace(String nazwa, double powierzchnia, double kosztNajmuMiejscaP){

        this.nazwa = nazwa;
        this.powierzchnia = powierzchnia;
        this.kosztNajmuMiejscaP = kosztNajmuMiejscaP;

        ID=parkingID++;


        try {
            czyZajete();
        } catch (TenantAlert tenantAlert) {

        }

    }

    public void czyZajete() throws TenantAlert {
        if(czyJestPojazd){
            usunPojazd();
            throw new TenantAlert(osoba + " - " + "Pojazd zostaje odholowany na parking strzezony");
        }else {

        }
    }



    public void wynajmijMiejsceParkingowe(Person p, Warehouse w, int iloscDni){

        double cenaMagazynu;

        if(w==null) {
            cenaMagazynu = 0;
        }else {
            cenaMagazynu = w.getKosztNajmu();
        }

        if(iloscDni>14){
            System.out.println("Maksymalny czas wynajmu to 14 dni");

        }else {
            try {
                if ((cenaMagazynu + kosztNajmuMiejscaP) > 1250) {
                    System.out.println("Suma kosztow magazynu i parkingu jest zbyt wysoka");
                } else {
                    iloscMiejsc[counter++] = this;
                    NajemcyMiejscParkingowych.put(p, this);
                    czyWynajety = true;
                    osoba = p;
                }


            } catch (IndexOutOfBoundsException e) {
                System.out.println("Ktos inny wynajmuje to miejsce parkingowe");
            }
        }

    }

    public void dodajPojazd(Vehicle v){
        if(!czyWynajety){
            System.out.println("Najpierw wynajmij miejsce zeby dodac pojazd");
        }else {
            try {
                pojazd[ktoryPojazd++] = v;
                czyJestPojazd=true;
                System.out.println("Pojazd zostal dodany na miejsce parkingowe");
            }catch (IndexOutOfBoundsException e){
                System.out.println("Moze byc tylko jeden pojazd na miejscu parkingowym");
            }
        }

    }

    public void usunPojazd(){

        if(pojazd[0]==null){
            System.out.println("Nic nie ma na tym miejscu");
        }else {
            pojazd[0] = null;
            czyJestPojazd = false;
            System.out.println("Pojazd zostal usuniety");
        }
    }

    public int getID() {
        return ID;
    }

    public void osobaWynajmujaca(){
        System.out.println(NajemcyMiejscParkingowych);
    }

    public String toString() {
        return "Miejsce Parkingowe{" +
                "nazwa='" + nazwa + '\'' +
                ", Stan=" + (!czyWynajety? "dostepny":"zajety") +
                ", ID=" + ID +
                '}';
    }
}
