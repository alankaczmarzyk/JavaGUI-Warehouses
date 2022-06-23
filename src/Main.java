import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;
import static java.util.stream.Collectors.toList;

public class Main {
    public static List<ConsumerWarehouse> pomieszczeniaMagazynow;
    public static List<ServiceWarehouse> pomieszczeniaSerwisow;
    public static List<CarServiceSpot> miejscaNaprawcze;
    public static List<IndependentCarServiceSpot> miejscaSerwisowe;
    public static Person osoba;
    public static Object obiekt;
    public static Vehicle pojazd;
    public static CarService serwisNaprawczy;
    public static ParkingSpace miejsceParkingowe;
    public static Warehouse magazyn;
    public static List<Person> listaOsob = new ArrayList<>();
    public static List<Warehouse> listaMagazynow = new ArrayList<>();
    public static List<Warehouse> listaWolnychMagazynow = new ArrayList<>();
    public static List<Warehouse> listaZajetychMagazynow = new ArrayList<>();
    public static Set<Object> listaObiektow = new HashSet<>();
    public static Set<Vehicle> listaPojazdow = new HashSet<>();
    public static List<ParkingSpace> listaMiejscParkingowych = new LinkedList<>();
    public static List<CarService> listaMiejscNaprawczychiSerwisowych = new LinkedList<>();
    public static List<ParkingSpace> listaWolnychMiejscParkingowych = new LinkedList<>();
    public static HashMap<Warehouse, Person> osobyUprawnione = new HashMap<>();
    public static List<Warehouse> listaMagazynowUprawnionych = new ArrayList<>();
    public static int numer;
    public static String text;
    public static CarServiceSpot miejsceNaprawcze;
    public static IndependentCarServiceSpot miejsceSerwisowe;
    public static boolean wpiszDobrze = false;
    public static boolean czyChceParking;
    public static Service serwis;

    public static void main(String[] args) throws Exception {
        int select;
        boolean exit = false;

        // TWORZENIE OBIEKTY
        serwis = new Service("U Zbycha", 1, 10, 50, 3, 1);

        ConsumerWarehouse cw1 = new ConsumerWarehouse("ABC", 2000, 500, LocalDate.of(2021, 2, 26), LocalDate.of(2022, 6, 24));
        ConsumerWarehouse cw2 = new ConsumerWarehouse("BCD", 300, 200, LocalDate.of(2021, 11, 28), LocalDate.of(2022, 6, 24));
        ConsumerWarehouse cw3 = new ConsumerWarehouse("DEF", 300, 10, LocalDate.of(2021, 7, 11), LocalDate.of(2022, 7, 1));
        ConsumerWarehouse cw4 = new ConsumerWarehouse("DGC", 300, 10, LocalDate.of(2021, 9, 11), LocalDate.of(2022, 7, 12));
        ConsumerWarehouse cw5 = new ConsumerWarehouse("EGS", 300, 10, LocalDate.of(2021, 4, 11), LocalDate.of(2022, 7, 4));
        ServiceWarehouse sw1 = new ServiceWarehouse("FGH", 45, 400, LocalDate.of(2021, 2, 26), LocalDate.of(2022, 7, 30));
        ServiceWarehouse sw2 = new ServiceWarehouse("IJK", 30, 1250, LocalDate.of(2021, 8, 8), LocalDate.of(2022, 7, 9));
        ServiceWarehouse sw3 = new ServiceWarehouse("FGH", 45, 400, LocalDate.of(2021, 3, 26), LocalDate.of(2022, 7, 17));
        ServiceWarehouse sw4 = new ServiceWarehouse("IJK", 30, 1250, LocalDate.of(2021, 9, 8), LocalDate.of(2022, 7, 15));
        ServiceWarehouse sw5 = new ServiceWarehouse("FGH", 45, 400, LocalDate.of(2021, 12, 26), LocalDate.of(2022, 7, 7));

        Person p1 = new Person("Adam", "Kowalski", 132323222, "Warszawa, Gorecka 5", LocalDate.of(2020, 6, 28));
        Person p2 = new Person("Paweł", "Jarosz", 232321232, "Czestochowa, Warynskiego 5", LocalDate.of(2019, 4, 6));
        Person p3 = new Person("Marek", "Wilusz", 023021202, "Warszawa, Mokotowska 5", LocalDate.of(2020, 11, 11));
        Person p4 = new Person("Adam", "Ozga", 1232320202, "Warszawa, Kielecka 25a", LocalDate.of(2021, 1, 26));
        Person p5 = new Person("Michał", "Browar", 523302102, "Raszyn, Warszawska 135", LocalDate.of(2021, 3, 7));

        ParkingSpace ps1 = new ParkingSpace("Miejsce A", 10, 200);
        ParkingSpace ps2 = new ParkingSpace("Miejsce B", 10, 200);
        ParkingSpace ps3 = new ParkingSpace("Miejsce C", 7, 150);

        Object o1 = new Object("rower", 20);
        Object o2 = new Object("pralka", 10, 5, 3);
        Object o3 = new Object("telewizor", 179);
        Object o4 = new Object("obraz", 2);
        Object o5 = new Object("wiadro", 2, 2, 2);

        CarServiceSpot Ics1 = new CarServiceSpot("XYZ", 100);
        CarServiceSpot Ics2 = new CarServiceSpot("ZYX", 20);
        CarServiceSpot Ics3 = new CarServiceSpot("YZX", 100);

        IndependentCarServiceSpot icss1 = new IndependentCarServiceSpot("ZZZ", 30);

        Motorcycle m1 = new Motorcycle("Ducati", "scigacz", 1000, 234);
        Amphibian a1 = new Amphibian("ISUZU", "wojskowy", 7000, 2);
        CityCar c1 = new CityCar("Toyota", "miejskie", 1200, "automatyczna");
        OffRoadCar of1 = new OffRoadCar("Nissan", "Offroadowy", 4200, "4x4");


        // DODAWANIE POMIESZCZEN, MIEJSC NAPRAWCZYCH
        pomieszczeniaMagazynow = List.of(ConsumerWarehouse.zwrocListe());
        pomieszczeniaSerwisow = List.of(ServiceWarehouse.zwrocListe());
        miejscaNaprawcze = List.of(CarServiceSpot.getListaMiejscNaprawczych());
        miejscaSerwisowe = List.of(IndependentCarServiceSpot.getListaMiejscSerwisowych());
        listaOsob = Person.getListaOsob();
        listaMagazynow = Warehouse.getWszystkieMagazyny();
        listaObiektow = Object.getListaObiektow();
        listaPojazdow = Vehicle.getListaPojazdow();
        listaMiejscParkingowych = ParkingSpace.getMiejscaParkingowe();
        listaMiejscNaprawczychiSerwisowych = CarService.getWszystkieMiejsca();

        // FUNKCJONALNOSC
            //your examples


        // MENU
        while (!exit) {
            System.out.println();
            System.out.println("----Menu----");
            System.out.println("WYBIERZ OPCJE:");
            System.out.println(" 1 - Wyjscie z programu");
            System.out.println(" 2 - Wybierz osobe");
            System.out.println(" 3 - Wyswietl informacje");
            System.out.println(" 4 - Wyswietl wolne pomieszczenia");
            System.out.println(" 5 - Wynajmij pomieszczenie");
            System.out.println(" 6 - Sprawdz zawartosc pomieszczenia");
            System.out.println(" 7 - Wloz przedmiot do magazynu");
            System.out.println(" 8 - Dodaj uprawnienia do magazynu");
            System.out.println(" 9 - Zaparkuj pojazd na miejscu parkingowym");
            System.out.println(" 10 - Zabierz przedmiot z magazynu");
            System.out.println(" 11 - Zabierz auto z parkingu");
            System.out.println(" 12 - Wynajmij miejsce serwisowe lub naprawcze");
            System.out.println(" 13 - Zglos potrzebe naprawy lub rozpocznij serwisowanie pojazdu");
            System.out.println(" 14 - Zakoncz naprawe pojazdu");
            System.out.println(" 15 - Zapisz aktualny stan");
            System.out.println(" 16 - Uruchom upływ czasu");


            Scanner sc = new Scanner(System.in);
            while (!sc.hasNextInt()) {
                sc.next();
            }
            select = sc.nextInt();
            switch (select) {

                case 1:
                    exit = true;
                    break;
                case 2:
                    wybierzOsobe(sc);
                    break;
                case 3:
                    if (osoba == null) {
                        System.out.println("Najpierw wybierz Osobe:");
                        wybierzOsobe(sc);
                    }
                    wyswietlInformacje();
                    break;
                case 4:
                    System.out.println("Lista wolnych magazynow");
                    listaWolnychMagazynow = getlistaWolnychMagazynow();
                    System.out.println(listaWolnychMagazynow);
                    break;
                case 5:
                    if (osoba == null) {
                        System.out.println("Najpierw wybierz Osobe:");
                        wybierzOsobe(sc);
                    }
                    System.out.println("Wybierz magazyn:");
                    wybierzWolnyMagazyn(sc);
                    wynajmijParking(sc);
                    break;
                case 6:
                    if (osoba == null) {
                        System.out.println("Najpierw wybierz Osobe:");
                        wybierzOsobe(sc);
                    }
                    if (osoba.listaPomieszczenWynajetych().isEmpty()) {
                        System.out.println("Osoba nic nie wynajmuje.");
                    } else {
                        wyswietlInformacje();
                        System.out.println("Wybierz id magazynu by zobaczyc jego zawartosc:");
                        wyswietlListePomieszczenWynajetych(sc);
                    }
                    break;
                case 7:
                    if (osoba == null) {
                        System.out.println("Wybierz osobe:");
                        wybierzOsobe(sc);
                    }
                    dodajPrzedmiotDoMagazynu(sc);
                    break;
                case 8:
                    if (osoba == null) {
                        System.out.println("Wybierz osobe ktorej chcesz nadac uprawnienia:");
                        wybierzOsobe(sc);
                    }
                    System.out.println("Wybierz magazyn do ktorego nadasz uprawnienia:");
                    wybierzMagazyn(sc);
                    magazyn.dodajUprawnienie(osoba);
                    break;
                case 9:
                    if (osoba == null) {
                        System.out.println("Wybierz osobe:");
                        wybierzOsobe(sc);
                    }
                    System.out.println("Wybierz Miejsce parkingowe:");
                    wyswietlMiejscaParkingowe(sc);
                    System.out.println("Wybierz pojazd:");
                    wybierzPojazd(sc);
                    miejsceParkingowe.dodajPojazd(osoba, pojazd);
                    break;
                case 10:
                    if (osoba == null) {
                        System.out.println("Wybierz osobe:");
                        wybierzOsobe(sc);
                    }
                    System.out.println("Wybierz magazyn:");
                    wybierzMagazyn(sc);
                    System.out.println("Wybierz przedmiot:");
                    System.out.println(magazyn.przedmiotyMagazynu());
                    if (magazyn.przedmiotyMagazynu().isEmpty()) {
                        System.out.println("Brak przedmiotow w magazynie.");
                        break;
                    }
                    numer = sc.nextInt();
                    listaObiektow.forEach(
                            O -> {
                                if (O.getID() == numer)
                                    obiekt = O;

                            }
                    );
                    magazyn.wyjmijPrzedmiot(osoba, obiekt);
                    break;
                case 11:
                    if (osoba == null) {
                        System.out.println("Wybierz osobe:");
                        wybierzOsobe(sc);
                    }
                    System.out.println("Wybierz miejsce parkingowe:");
                    wyswietlMiejscaParkingowe(sc);

                    miejsceParkingowe.usunPojazd(osoba);
                    break;
                case 12:
                    if (osoba == null) {
                        System.out.println("Wybierz osobe:");
                        wybierzOsobe(sc);
                    }
                    System.out.println("Wybierz pojazd:");
                    wybierzPojazd(sc);
                    wybierzSerwisSamochodowy(sc);
                    serwisNaprawczy.wynajmijMiejsce(osoba, pojazd);
                    break;
                case 13:
                    if (osoba == null) {
                        System.out.println("Wybierz osobe:");
                        wybierzOsobe(sc);
                    }
                    System.out.println("Wybierz pojazd:");
                    wybierzPojazd(sc);
                    System.out.println("Czy chcesz samodzielnie serwisowac pojazd? Wpisz: T jesli TAK. Wpisz dowolny znak jesli ma byc naprawiany przez mechaników:");
                    text = sc.next();
                    if (text.equals("T") || text.equals("t") || text.equals("TAK") || text.equals("tak")) {
                        System.out.println("Wybrano samodzielna naprawe.");
                        System.out.println("Wybierz miejsce serwisowe:");
                        wybierzMiejsceSerwisowe(sc);
                        miejsceSerwisowe.ropozcznijSamodzielnaNaprawe(osoba, pojazd);
                    } else {
                        System.out.println("Wybrano naprawe przez mechaników.");
                        System.out.println("Wybierz miejsce naprawcze:");
                        wybierzMiejsceNaprawcze(sc);
                        miejsceNaprawcze.rozpocznijNaprawe(pojazd);
                    }
                    break;
                case 14:
                    System.out.println("Jaki rodzaj naprawy chcesz zakonczyc? Wpisz: TAK jesli Samodzielna lub wpisz dowolny znak jesli NIE.");
                    text = sc.next();
                    if (text.equals("t") || text.equals("T") || text.equals("TAK") || text.equals("tak") || text.equals("Tak")) {
                        zakonczNapraweSerwisowego(sc);
                    } else {
                        zakonczNapraweMiejscaNaprawczego(sc);
                    }
                    break;
                case 15:
                    writeToFile();
                    break;
                case 16:
                    TimeLapseThread t1 = new TimeLapseThread();
                    LeaseCheckingThread t2 = new LeaseCheckingThread();
                    t1.start();
                    t2.start();
                    break;
                default:
                    System.out.println("Prosze podac numer opcji zgodny z MENU.");
            }
        }
    }

    private static void wybierzOsobe(Scanner sc) {
        System.out.println(listaOsob);
        numer = sc.nextInt();

        listaOsob.forEach(
                P -> {
                    if (P.getId() == numer)
                        osoba = P;
                }
        );

        System.out.println("Wybrano: " + osoba.imie + " " + osoba.nazwisko);
        System.out.println();
    }

    private static void wybierzMagazyn(Scanner sc) {
        System.out.println(listaMagazynow);
        numer = sc.nextInt();
        listaMagazynow.forEach(
                W -> {
                    if (W.getId() == numer) {
                        magazyn = W;
                    }
                }

        );

    }

    public static void zakonczNapraweSerwisowego(Scanner sc) {

        Map<IndependentCarServiceSpot, Vehicle> lista = IndependentCarServiceSpot.getListaSerwisowanychPojazdow();
        if (!lista.isEmpty()) {
            if(osoba==null) {
                System.out.println("Wybierz osobe:");
                wybierzOsobe(sc);
            }
            System.out.println("Wybierz miejsce serwisowe:");
            System.out.println(lista);
            numer = sc.nextInt();
            for (Map.Entry<IndependentCarServiceSpot, Vehicle> entry : lista.entrySet()) {
                if (entry.getKey().getId() == numer)
                    miejsceSerwisowe = entry.getKey();

            }
            miejsceSerwisowe.zakonczSamodzielnaNaprawe(osoba);
        } else
            System.out.println("Nie ma obecnie zadnych napraw.");


    }

    private static void zakonczNapraweMiejscaNaprawczego(Scanner sc) {
        Map<CarServiceSpot, Vehicle> lista = CarServiceSpot.getListaWszystkichPojazdowNaprawianych();
        if (!lista.isEmpty()) {
            System.out.println("Wybierz miejsce Naprawcze:");
            System.out.println(lista);
            numer = sc.nextInt();
            for (Map.Entry<CarServiceSpot, Vehicle> entry : lista.entrySet()) {
                if (entry.getKey().getId() == numer)
                    miejsceNaprawcze = entry.getKey();

            }
            miejsceNaprawcze.zakonczNaprawe();
        } else
            System.out.println("Nie ma obecnie zadnych napraw.");
    }

    private static void wybierzWolnyMagazyn(Scanner sc) {
        listaWolnychMagazynow = getlistaWolnychMagazynow();
        System.out.println(listaWolnychMagazynow);
        numer = sc.nextInt();

        listaWolnychMagazynow.forEach(
                W -> {
                    if (W.getId() == numer) {
                        magazyn = W;
                    }
                }

        );
    }

    private static void wynajmijParking(Scanner sc) {

        while (!wpiszDobrze) {
            System.out.println("Czy chcesz wynajac miejsce parkingowe? Wpisz:  T  jeśli tak, wpisz:  N  jeśli nie:");
            text = sc.next();
            if (text.equals("T") || text.equals("t") || text.equals("tak") || text.equals("TAK") || text.equals("Tak")) {
                wpiszDobrze = true;
                czyChceParking = true;
            } else if (text.equals("N") || text.equals("n") || text.equals("nie") || text.equals("NIE")) {
                wpiszDobrze = true;
                czyChceParking = false;
            } else {
                System.out.println("Nie wpisales dobrego znaku.");
            }
        }

        if (czyChceParking) {
            System.out.println("Wybierz miejsce parkingowe ktore chcesz wynajac.");
            wybierzMiejsceParkingowe(sc);
            magazyn.wynajmijMagazyn(osoba, miejsceParkingowe);
        } else {
            magazyn.wynajmijMagazyn(osoba, null);
        }
        wpiszDobrze=false;
    }

    private static void wyswietlListePomieszczenWynajetych(Scanner sc) {
        numer = sc.nextInt();
        osoba.listaPomieszczenWynajetych().forEach(
                M -> {
                    if (M.getId() == numer) {
                        magazyn = M;
                    }
                }
        );
        System.out.print(magazyn.przedmiotyMagazynu());

    }

    private static void dodajPrzedmiotDoMagazynu(Scanner sc) throws TooManyThingsException {

        for (Map.Entry<Warehouse, Person> entry : Warehouse.osobyUprawnione.entrySet()) {
            Warehouse key = entry.getKey();
            Person value = entry.getValue();
            if (value.equals(osoba) && !listaMagazynowUprawnionych.contains(key)) {
                listaMagazynowUprawnionych.add(key);
            }
        }
        if (!listaMagazynowUprawnionych.isEmpty()) {
            System.out.println("Wybierz magazyn do ktorego chcesz wlozyc przedmiot:");
            System.out.println(listaMagazynowUprawnionych);
            numer = sc.nextInt();
            listaMagazynowUprawnionych.forEach(
                    W -> {
                        if (W.getId() == numer) {
                            magazyn = W;
                        }
                    });
            System.out.println("Wybierz przedmiot:");
            magazyn.przedmiotyMagazynu();
            wybierzPrzedmiot(sc);
            magazyn.wlozPrzedmiot(osoba, obiekt);
        } else {
            System.out.println("Nie masz uprawnien do zadnego magazynu.");
        }
    }

    private static void wybierzMiejsceParkingowe(Scanner sc) {
        wyswietlWolneMiejscaParkingowe();
        numer = sc.nextInt();

        listaWolnychMiejscParkingowych.forEach(
                L -> {
                    if (L.getID() == numer)
                        miejsceParkingowe = L;
                }
        );
    }

    private static void wyswietlMiejscaParkingowe(Scanner sc) {

        System.out.println(listaMiejscParkingowych);

        numer = sc.nextInt();

        listaMiejscParkingowych.forEach(
                L -> {
                    if (L.getID() == numer)
                        miejsceParkingowe = L;

                }

        );
    }

    private static void wybierzPrzedmiot(Scanner sc) {
        System.out.println(listaObiektow);
        numer = sc.nextInt();
        listaObiektow.forEach(
                O -> {
                    if (O.getID() == numer)
                        obiekt = O;
                }
        );
    }

    private static void wybierzPojazd(Scanner sc) {
        System.out.println(listaPojazdow);
        numer = sc.nextInt();
        listaPojazdow.forEach(
                V -> {
                    if (V.getIDpojazdu() == numer)
                        pojazd = V;
                }
        );
        System.out.println("Wybrano: "+pojazd);
        System.out.println();
    }

    private static void wybierzMiejsceNaprawcze(Scanner sc) {
        System.out.println(miejscaNaprawcze);
        numer = sc.nextInt();
        miejscaNaprawcze.forEach(
                V -> {
                    if (V.getId() == numer)
                        miejsceNaprawcze = V;

                }

        );


    }

    private static void wybierzMiejsceSerwisowe(Scanner sc) {
        System.out.println(miejscaSerwisowe);
        numer = sc.nextInt();
        miejscaSerwisowe.forEach(
                V -> {
                    if (V.getId() == numer)
                        miejsceSerwisowe = V;

                }

        );

    }

    private static void wybierzSerwisSamochodowy(Scanner sc) {
        System.out.println("Wybierz miejsce ktore chcesz wynajac");
        System.out.println(listaMiejscNaprawczychiSerwisowych);
        numer = sc.nextInt();
        listaMiejscNaprawczychiSerwisowych.forEach(
                LMP -> {
                    if (LMP.getId() == numer)
                        serwisNaprawczy = LMP;
                }
        );
    }

    public static List<Warehouse> getlistaWolnychMagazynow() {
        listaWolnychMagazynow = listaMagazynow.stream()
                .filter(e -> !e.czyZajety)
                .collect(toList());

        return listaWolnychMagazynow;

    }

    public static List<Warehouse> getListaZajetychMagazynow() {
        listaZajetychMagazynow = listaMagazynow.stream()
                .filter(e -> e.czyZajety)
                .collect(toList());

        return listaZajetychMagazynow;

    }

    public static void wyswietlWolneMiejscaParkingowe() {
        listaWolnychMiejscParkingowych = listaMiejscParkingowych.stream()
                .filter(e -> !e.czyWynajety)
                .collect(toList());

        System.out.println(listaWolnychMiejscParkingowych);
    }

    public static void wyswietlInformacje() {
        System.out.println(osoba.toString());
        System.out.print("Wynajete miejsca: ");
        osoba.listaPomieszczenWynajetych().forEach(info -> {
            System.out.println(info);
        });
        if (osoba.listaPomieszczenWynajetych().isEmpty()) {
            System.out.print("brak. ");
        }

        List<Warehouse> magazyny = new LinkedList<>();
        for (Map.Entry<Warehouse, Person> os : Warehouse.osobyUprawnione.entrySet()) {
            Warehouse key = os.getKey();
            Person value = os.getValue();

            if (value.equals(osoba)) {
                magazyny.add(key);
            }
        }
        System.out.println("Posiadane uprawnienia:" + magazyny);
    }

    public static void getPrzedmiotyMagazyznu(PrintWriter writer) {
        writer.println("Przedmioty w magazynach: ");
        for (Warehouse l : listaMagazynow) {
            if(!l.przedmiotyMagazynu().isEmpty()) writer.println(l.przedmiotyMagazynu());
        }
        writer.println();
    }

    public static Person getOsoba() {
        return osoba;
    }

    public static Map<CarServiceSpot,Queue<Vehicle>> getKolejkaOczekujacychNaNaprawe(){
        Map<CarServiceSpot,Queue<Vehicle>> map = new HashMap<>();
        for (CarServiceSpot c : miejscaNaprawcze) {
            if(!c.getKolejkaOczekujacych().isEmpty())
            map.put(c,c.getKolejkaOczekujacych());
        }
        return map;
    }

    public static Map<IndependentCarServiceSpot,Queue<Vehicle>> getKolejkaOczekujacychNaSerwis(){
        Map<IndependentCarServiceSpot,Queue<Vehicle>> map = new HashMap<>();
        for (IndependentCarServiceSpot c : miejscaSerwisowe) {
            if(!c.getKolejkaOczekujacychPojazdow().isEmpty())
                map.put(c,c.getKolejkaOczekujacychPojazdow());
        }
        return map;
    }

    public static Map<CarService, Set<Vehicle>> getHistoriaNapraw(){
        Map<CarService, Set<Vehicle>> map = CarService.getHistoriaNapraw();
        return map;
    }

    public static void writeToFile() {
        PrintWriter writer = null;
        try {
            File file = new File("warehouses.txt");
            file.createNewFile();
            writer = new PrintWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        writer.println(serwis);
        writer.println();
        voidCollection("Lista pomieszczen: ", writer, listaMagazynow);
        voidCollection("Wolne magazyny: ", writer, getlistaWolnychMagazynow());
        voidCollection("Zajete magazyny: ", writer, getListaZajetychMagazynow());
        voidCollection("Lista osob: ", writer, listaOsob);
        voidCollection("Lista przedmiotow: ", writer, listaObiektow);
        voidCollection("Lista osob wynajmujacych: ", writer, Warehouse.getOsobyWynajmujace());
        writer.println();
        getPrzedmiotyMagazyznu(writer);
        writer.println();
        writer.close();

        PrintWriter writer2 = null;
        try {
            File file2 = new File("services.txt");
            file2.createNewFile();
            writer2 = new PrintWriter(file2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        writer2.println(serwis);
        writer2.println();
        voidCollection("Miejsca parkingowe: ",writer2,listaMiejscParkingowych);
        voidCollection("Miejsca serwisowe: ",writer2, miejscaSerwisowe);
        voidCollection("Miejsca naprawcze:",writer2,miejscaNaprawcze);
        voidCollection("Lista pojazdow: " ,writer2,listaPojazdow);
        voidCollection2("Lista pojazdow naprawianych: ",writer2, CarServiceSpot.getListaWszystkichPojazdowNaprawianych());
        voidCollection4("Lista oczekujacych na naprawe: ",writer2, getKolejkaOczekujacychNaNaprawe());
        voidCollection3("Lista pojazdow serwisowanych",writer2, IndependentCarServiceSpot.getListaWszystkichPojazdowSerwisowanych());
        voidCollection5("Lista oczekujacych na serwis: ",writer2, getKolejkaOczekujacychNaSerwis());
        voidCollection6("Historia napraw: ",writer2, getHistoriaNapraw());

        writer2.println();
        writer2.close();


    }

    public static <T> void voidCollection(String message, PrintWriter wr, Collection<T> collection) {
        wr.println(message);
        collection.forEach(wr::println);
        wr.println();

    }

    public static void voidCollection(String message, PrintWriter wr, Map<Person, List<Warehouse>> collection) {
        wr.println(message);
        collection.forEach((person, warehouses) -> wr.println(person + " " + warehouses));
        wr.println();
    }

    public static void voidCollection2(String message, PrintWriter wr, Map<CarServiceSpot, Vehicle> collection) {

        wr.println(message);
        collection.forEach((carServiceSpot, vehicle) -> wr.println(carServiceSpot + ":  " + vehicle));
        wr.println();
    }

    public static void voidCollection3(String message, PrintWriter wr, Map<IndependentCarServiceSpot, Vehicle> collection) {
        wr.println(message);
        collection.forEach((carServiceSpot, vehicle) -> wr.println(carServiceSpot + ":  " + vehicle));
        wr.println();
    }

    public static void voidCollection4(String message, PrintWriter wr, Map<CarServiceSpot,Queue<Vehicle>> collection) {
        wr.println(message);
        collection.forEach((c, v) -> wr.println(c + ":  " + v));
        wr.println();
    }

    public static void voidCollection5(String message, PrintWriter wr, Map<IndependentCarServiceSpot,Queue<Vehicle>> collection) {
        wr.println(message);
        collection.forEach((c, v) -> wr.println(c + ":  " + v));
        wr.println();
    }

    public static void voidCollection6(String message, PrintWriter wr, Map<CarService, Set<Vehicle>> collection){
        wr.println(message);
        collection.forEach((c, v) -> wr.println(c + ":  " + v));
        wr.println();
    }
}
