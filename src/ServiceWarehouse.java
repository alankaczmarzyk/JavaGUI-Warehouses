import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServiceWarehouse extends Warehouse {

    private int ID;
    private int counter=0;
    private static int counter2=0;
    private Person[] wlasciciel = new Person[1];
    public  List<Person> osobySW = new ArrayList<>();
    public  List<Object> obiektySW = new ArrayList<>();
    private double kosztMagazynu;
    private double kosztParkingu;
    public static HashMap<ServiceWarehouse, List<Person>> NajemcyPomieszczenSerwisowych = new HashMap<>();
    private HashMap<Warehouse, List<Object>> listaPrzedmiotowSW= new HashMap<>();
    private static double sumaObjetosciPrzedmiotow =0;
    private static ServiceWarehouse[] listaPomieszczenieSerwisowych;


    public ServiceWarehouse(String nazwa, double objetosc, double kosztNajmu, LocalDate dataRozpoczeciaNajmu, LocalDate dataZakonczeniaNajmu) {

        super(nazwa, objetosc, kosztNajmu, dataRozpoczeciaNajmu, dataZakonczeniaNajmu);
        ID= idPomieszczenia++;

        kosztMagazynu=kosztNajmu;
        dodajMagazyn();
        czyKonsumecki=false;

    }



    public ServiceWarehouse(String nazwa, double dlugosc, double szerokosc, double wysokosc, double kosztNajmu, LocalDate dataRozpoczeciaNajmu, LocalDate dataZakonczeniaNajmu ) {

        super(nazwa, dlugosc, szerokosc, wysokosc, kosztNajmu, dataRozpoczeciaNajmu, dataZakonczeniaNajmu);
        ID= idPomieszczenia++;

        kosztMagazynu=kosztNajmu;

        dodajMagazyn();
    }

    @Override
    public void dodajMagazyn() {

        int count = Service.getPomieszczeniaSerwisowowe();

        if (listaPomieszczenieSerwisowych == null) {
            listaPomieszczenieSerwisowych = new ServiceWarehouse[count];
            listaPomieszczenieSerwisowych[counter2++] = this;
            Warehouse.wszystkieMagazyny.add(this);

        } else {
            try {
                listaPomieszczenieSerwisowych[counter2++] = this;
                Warehouse.wszystkieMagazyny.add(this);
            } catch (IndexOutOfBoundsException e) {
                throw new IndexOutOfBoundsException("Przekroczono maksymalna ilosc magazynow serwisowych.");

            }

        }
    }

    public static ServiceWarehouse[] zwrocListe() {
        return listaPomieszczenieSerwisowych;
    }

    @Override
    public int getId() {
        return ID;
    }


    @Override
    public String toString(){

        return super.toString() + "Id:"+ this.ID + " [Serwisowy]";
    }

    @Override
    public void wynajmijMagazyn(Person wl, ParkingSpace ps) {

        try {
                wlasciciel[counter++] = wl;
                System.out.println(wl + " wynajmuje " + toString());
                osobySW.add(wl);
                osobyUprawnione.put(this,wl);
                osobyWynajmujace.computeIfAbsent(wl, k -> new ArrayList<>()).add(this);
                NajemcyPomieszczenSerwisowych.put(this, osobySW);
                czyZajety=true;
                wl.dodajPomieszczenie(this);


            if(ps==null){
                kosztParkingu=0;
            }else {
                kosztParkingu = ps.getKosztNajmuMiejscaP();

                if(kosztParkingu+kosztMagazynu>1250){
                    System.out.println("Suma kosztow najmu przekracza 1250 zl.");
                }else {
                    ps.wynajmijMiejsceParkingowe(wl,this,14);

                }

            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Ten magazyn jest juz wynajety.");
        }
    }

    @Override
    public void dodajUprawnienie(Person p) {

        if(osobySW.contains(p)){
            System.out.println("Ta osoba ma juz uprawnienie!");
        }else {
            osobySW.add(p);
            osobyUprawnione.put(this,p);
            NajemcyPomieszczenSerwisowych.put(this,osobySW);
            System.out.println("Dodano uprawnienie.");
        }
    }

    @Override
    public void odbierzUprawnienie(Person p) {

        if (p == osobySW.get(0)) {
            System.out.println("Nie mozesz zabrac uprawnienia wlascicielowi!");
        } else {
            osobySW.remove(p);
            osobyUprawnione.remove(this);
            NajemcyPomieszczenSerwisowych.put(this, osobySW);
        }
    }

    @Override
    public void wlozPrzedmiot(Person p, Object o) throws TooManyThingsException {

        sumaObjetosciPrzedmiotow+=o.getPolePowierzchni();
        if(this.getObjetosc()>sumaObjetosciPrzedmiotow) {
            if (osobySW.contains(p)) {
                obiektySW.add(o);
                listaPrzedmiotowSW.put(this, obiektySW);
                System.out.println("Dodales przedmiot do magazynu.");
            } else {
                System.out.println("Nie masz uprawnien do wlozenia przedmiotu.");
            }
        }else{
            throw new TooManyThingsException("Remove some old itemvs to insert a new item.");
        }

    }

    @Override
    public void wyjmijPrzedmiot(Person p, Object o) {

        if(osobySW.contains(p)){
            if(obiektySW.contains(o)) {
                obiektySW.remove(o);
                listaPrzedmiotowSW.put(this, obiektySW);
                System.out.println("Wyjales przedmiot z magazynu.");
            }else {
                System.out.println("Nie ma takiego obiektu w magazynie.");
            }
        }else {
            System.out.println("Nie masz uprawnien do wyjmowania przedmiotu.");
        }

    }


    @Override
    public void osobyUprawnione() {
        System.out.println(NajemcyPomieszczenSerwisowych);
    }

    @Override
    public HashMap<Warehouse, List<Object>> przedmiotyMagazynu() {
        return listaPrzedmiotowSW;
    }

    @Override
    public HashMap<Warehouse, Person> Najemcy() {
        return osobyUprawnione;
    }


}
