import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConsumerWarehouse extends Warehouse {

    private int ID;
    private int counter = 0;
    private static int counter2 = 0;
    private Person[] wlasciciel = new Person[1];
    public List<Person> osobyCW = new ArrayList<>();
    public List<Object> obiektyCW = new ArrayList<>();
    private double kosztMagazynu;
    private double kosztParkingu;
    public HashMap<Warehouse, List<Person>> NajemcyPomieszczenMagazynowych = new HashMap<>();
    private HashMap<Warehouse, List<Object>> listaPrzedmiotowCW = new HashMap<>();
    private static double sumaObjetosciPrzedmiotow = 0;
    private static ConsumerWarehouse[] listaPomieszczenieMagazynowych;


    public ConsumerWarehouse(String nazwa, double objetosc, double kosztNajmu, LocalDate dataRozpoczeciaNajmu, LocalDate dataZakonczeniaNajmu) {

        super(nazwa, objetosc, kosztNajmu, dataRozpoczeciaNajmu, dataZakonczeniaNajmu);
        ID = idPomieszczenia++;

        kosztMagazynu = kosztNajmu;
        dodajMagazyn();
    }

    public ConsumerWarehouse(String nazwa, double dlugosc, double szerokosc, double wysokosc, double kosztNajmu, LocalDate dataRozpoczeciaNajmu, LocalDate dataZakonczeniaNajmu) {

        super(nazwa, dlugosc, szerokosc, wysokosc, kosztNajmu, dataRozpoczeciaNajmu, dataZakonczeniaNajmu);
        ID = idPomieszczenia++;

        kosztMagazynu = kosztNajmu;
        dodajMagazyn();
        czyKonsumecki = true;
    }

    @Override
    public void dodajMagazyn() {

        int count = Service.getPomieszczeniaMagazynowe();

        if (listaPomieszczenieMagazynowych == null) {
            listaPomieszczenieMagazynowych = new ConsumerWarehouse[count];
            listaPomieszczenieMagazynowych[counter2++] = this;
            Warehouse.wszystkieMagazyny.add(this);
        } else {
            try {
                listaPomieszczenieMagazynowych[counter2++] = this;
                Warehouse.wszystkieMagazyny.add(this);
            } catch (IndexOutOfBoundsException e) {
                throw new IndexOutOfBoundsException("Przekroczono maksymalna ilosc magazynow konsumenckich.");

            }

        }
    }


    public static ConsumerWarehouse[] zwrocListe() {
        return listaPomieszczenieMagazynowych;
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
    public void wynajmijMagazyn(Person wl, ParkingSpace ps) {

        try {
            wlasciciel[counter++] = wl;
            System.out.println(wl + " wynajmuje " + toString());
            osobyCW.add(wl);
            osobyUprawnione.put(this, wl);
            osobyWynajmujace.computeIfAbsent(wl, k -> new ArrayList<>()).add(this);
            NajemcyPomieszczenMagazynowych.put(this, osobyCW);
            czyZajety = true;
            wl.dodajPomieszczenie(this);

            if (ps == null) {
                kosztParkingu = 0;
            } else {
                kosztParkingu = ps.getKosztNajmuMiejscaP();
                if (kosztParkingu + kosztMagazynu > 1250) {
                    System.out.println("Suma kosztow najmu przekracza 1250 zl.");
                } else {
                    ps.wynajmijMiejsceParkingowe(wl, this, 14);
                }
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Ten magazyn jest juz wynajety.");
        }
    }

    @Override
    public void dodajUprawnienie(Person p) {

        if (osobyCW.contains(p)) {
            System.out.println("Ta osoba ma juz uprawnienie!");
        } else {
            osobyCW.add(p);
            NajemcyPomieszczenMagazynowych.put(this, osobyCW);
            osobyUprawnione.put(this, p);
            System.out.println("Dodano uprawnienie.");
        }


    }


    @Override
    public void odbierzUprawnienie(Person p) {

        if (p == osobyCW.get(0)) {
            System.out.println("Nie mozesz zabrac uprawnienia wlascicielowi!");
        } else {
            osobyCW.remove(p);
            NajemcyPomieszczenMagazynowych.put(this, osobyCW);
            osobyUprawnione.remove(this);
            System.out.println("Zabrano uprawnienie");
        }
    }


    @Override
    public void wlozPrzedmiot(Person p, Object o) throws TooManyThingsException {

        sumaObjetosciPrzedmiotow += o.getPolePowierzchni();
        if (this.getObjetosc() >= sumaObjetosciPrzedmiotow) {
            if (osobyCW.contains(p)) {
                obiektyCW.add(o);
                listaPrzedmiotowCW.put(this, obiektyCW);
                System.out.println("Dodales przedmiot do magazynu.");
            } else {
                System.out.println("Nie masz uprawnien do wlozenia przedmiotu.");
            }
        } else {
            throw new TooManyThingsException("Remove some old items to insert a new item.");
        }

    }

    @Override
    public void wyjmijPrzedmiot(Person p, Object o) {

        if (osobyCW.contains(p)) {
            if (obiektyCW.contains(o)) {
                obiektyCW.remove(o);
                listaPrzedmiotowCW.put(this, obiektyCW);
                System.out.println("Wyjales przedmiot z magazynu.");
            } else {
                System.out.println("Nie ma takiego obiektu w magazynie.");
            }
        } else {
            System.out.println("Nie masz uprawnien do wyjmowania przedmiotu.");
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
        return osobyUprawnione;
    }


}
