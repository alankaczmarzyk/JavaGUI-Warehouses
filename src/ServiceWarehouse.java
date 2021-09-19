import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServiceWarehouse extends Warehouse {

    private int ID;
    private int counter=0;
    private Person[] wlasciciel = new Person[1];
    public  List<Person> osobySW = new ArrayList<>();
    public  List<Object> obiektySW = new ArrayList<>();
    public static HashMap<ServiceWarehouse, List<Person>> NajemcyPomieszczenSerwisowych = new HashMap<>();
    private HashMap<Warehouse, List<Object>> listaPrzedmiotowSW= new HashMap<>();
    private static double sumaPolPowierzchni=0;



    public ServiceWarehouse(String nazwa, double objetosc, double kosztNajmu, LocalDate dataRozpoczeciaNajmu, LocalDate dataZakonczeniaNajmu) {

        super(nazwa, objetosc, kosztNajmu, dataRozpoczeciaNajmu, dataZakonczeniaNajmu);
        ID= idPomieszczenia++;

        sumaKosztowMagazynu=kosztNajmu;
    }


    public ServiceWarehouse(String nazwa, double dlugosc, double szerokosc, double wysokosc, double kosztNajmu, LocalDate dataRozpoczeciaNajmu, LocalDate dataZakonczeniaNajmu ) {

        super(nazwa, dlugosc, szerokosc, wysokosc, kosztNajmu, dataRozpoczeciaNajmu, dataZakonczeniaNajmu);
        ID= idPomieszczenia++;

        sumaKosztowMagazynu=kosztNajmu;
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
    public void wynajmijMagazyn(Person wl) {

        try {
            if (sumaKosztowMagazynu > 1250) {
                System.out.println("Ten magazyn jest za drogi");
            } else {
                wlasciciel[counter++] = wl;
                System.out.println(wl + " wynajmuje " + toString());
                osobySW.add(wl);
                NajemcyPomieszczenSerwisowych.put(this, osobySW);
                Najemcy.put(this,wl);
                czyZajety=true;
                wl.dodajPomieszczenie(this);

            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Magazyn jest juz wynajety");
        }
    }

    @Override
    public void dodajUprawnienie(Person p) {

        if(osobySW.contains(p)){
            System.out.println("Ta osoba ma juz uprawnienie!");
        }else {
            osobySW.add(p);
            NajemcyPomieszczenSerwisowych.put(this,osobySW);
        }
    }

    @Override
    public void odbierzUprawnienie(Person p) {

        if (p == osobySW.get(0)) {
            System.out.println("Nie mozesz zabrac uprawnienia wlascicielowi!");
        } else {
            osobySW.remove(p);
            NajemcyPomieszczenSerwisowych.put(this, osobySW);
        }
    }

    @Override
    public void wlozPrzedmiot(Person p, Object o) throws TooManyThingsException {

        sumaPolPowierzchni+=o.getPolePowierzchni();
        if(this.objetosc>sumaPolPowierzchni) {

            if (osobySW.contains(p)) {
                obiektySW.add(o);
                listaPrzedmiotowSW.put(this, obiektySW);
                System.out.println("Dodales przedmiot do magazynu");
            } else {
                System.out.println("Nie masz uprawnien do wlozenia przedmiotu");
            }
        }else{
            throw new TooManyThingsException("Remove some old itemvs to insert a new item");
        }

    }

    @Override
    public void wyjmijPrzedmiot(Person p, Object o) {

        if(osobySW.contains(p)){
            obiektySW.remove(o);
            listaPrzedmiotowSW.put(this,obiektySW);
            System.out.println("Wyjales przedmiot z magazynu");
        }else {
            System.out.println("Nie masz uprawnien do wyjmowania przedmiotu");
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
        return Najemcy;
    }


}
