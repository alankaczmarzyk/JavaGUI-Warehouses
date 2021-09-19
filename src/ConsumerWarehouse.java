import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConsumerWarehouse extends Warehouse  {

    private int ID;
    private int counter = 0;
    private Person[] wlasciciel = new Person[1];
    public  List<Person> osobyCW = new ArrayList<>();
    public  List<Object> obiektyCW = new ArrayList<>();
    public static HashMap<Warehouse, List<Person>> NajemcyPomieszczenMagazynowych = new HashMap<>();
    private HashMap<Warehouse, List<Object>> listaPrzedmiotowCW= new HashMap<>();
    private static double sumaPolPowierzchni=0;

    public ConsumerWarehouse(String nazwa, double objetosc, double kosztNajmu, LocalDate dataRozpoczeciaNajmu, LocalDate dataZakonczeniaNajmu) {

        super(nazwa, objetosc, kosztNajmu, dataRozpoczeciaNajmu,  dataZakonczeniaNajmu);
        ID = idPomieszczenia++;

        sumaKosztowMagazynu=kosztNajmu;
    }

    public ConsumerWarehouse(String nazwa, double dlugosc, double szerokosc, double wysokosc, double kosztNajmu, LocalDate dataRozpoczeciaNajmu, LocalDate dataZakonczeniaNajmu ) {

        super(nazwa, dlugosc, szerokosc, wysokosc, kosztNajmu, dataRozpoczeciaNajmu, dataZakonczeniaNajmu);
        ID = idPomieszczenia++;

        sumaKosztowMagazynu=kosztNajmu;
    }

    @Override
    public int getId() {
        return ID;
    }


    @Override
    public String toString() {

        return super.toString() + "Id:" + this.ID + " [Konsumecki]";

    }

    @Override
    public void wynajmijMagazyn(Person wl) {

        try {
            if (sumaKosztowMagazynu > 1250) {
                System.out.println("Ten magazyn jest za drogi");

            } else {
                wlasciciel[counter++] = wl;
                System.out.println(wl + " wynajmuje " + toString());
                osobyCW.add(wl);
                NajemcyPomieszczenMagazynowych.put(this, osobyCW);
                Najemcy.put(this,wl);
                czyZajety=true;
                wl.dodajPomieszczenie(this);
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Magazyn jest juz wynajety");
        }
    }

    @Override
    public void dodajUprawnienie(Person p) {

        if (osobyCW.contains(p)) {
            System.out.println("Ta osoba ma juz uprawnienie!");
        } else {
            osobyCW.add(p);
            NajemcyPomieszczenMagazynowych.put(this, osobyCW);
            System.out.println("Dodano uprawnienie");
        }


    }


    @Override
    public void odbierzUprawnienie(Person p) {

        if (p == osobyCW.get(0)) {
            System.out.println("Nie mozesz zabrac uprawnienia wlascicielowi!");
        } else {
            osobyCW.remove(p);
            NajemcyPomieszczenMagazynowych.put(this, osobyCW);
            System.out.println("Zabrano uprawnienie");
        }
    }


    @Override
    public void wlozPrzedmiot(Person p, Object o) throws TooManyThingsException {

        sumaPolPowierzchni= sumaPolPowierzchni+o.getPolePowierzchni();
        if(this.objetosc>sumaPolPowierzchni){

            if (osobyCW.contains(p)) {
                obiektyCW.add(o);
                listaPrzedmiotowCW.put(this, obiektyCW);
                System.out.println("Dodales przedmiot do magazynu");
            } else {
                System.out.println("Nie masz uprawnien do wlozenia przedmiotu");
            }
        }else {
            throw new TooManyThingsException("Remove some old items to insert a new item");
        }

    }

    @Override
    public void wyjmijPrzedmiot(Person p, Object o) {

        if(osobyCW.contains(p)){
            obiektyCW.remove(o);
            listaPrzedmiotowCW.put(this,obiektyCW);
            System.out.println("Wyjales przedmiot z magazynu");
        }else {
            System.out.println("Nie masz uprawnien do wyjmowania przedmiotu");
        }
    }


    @Override
    public void osobyUprawnione() {
        System.out.println(NajemcyPomieszczenMagazynowych);
    }

    @Override
    public HashMap<Warehouse, List<Object>> przedmiotyMagazynu() {
        return listaPrzedmiotowCW;
    }

    @Override
    public HashMap<Warehouse, Person> Najemcy() {
        return Najemcy;
    }



}
