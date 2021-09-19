import org.w3c.dom.ls.LSOutput;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.*;

public class Main {

    public static ConsumerWarehouse[] pomieszczeniaMagazynow;
    public static ServiceWarehouse[] pomieszczeniaSerwisow;
    public static IndependentCarServiceSpot[] miejscaNaprawcze;
    public static IndependentCarServiceSpot[] miejscaSerwisowe;
    public static int iloscObiektowM=0;
    public static int iloscObiektowS=0;
    public static int iloscMiejscN=0;
    public static int iloscMiejscS=0;
    public static Person osoba;
    public static Object obiekt;
    public static Vehicle pojazd;
    public static IndependentCarServiceSpot serwis;
    public static ParkingSpace miejsceParkingowe;
    public static Warehouse warehouse;
    public static List<Person> listaOsob= new ArrayList<>();
    public static List<Warehouse> listaMagazynow= new ArrayList<>();
    public static List<Warehouse> listaWolnychMagazynow = new ArrayList<>();
    public static List<Warehouse> listaZajetychMagazynow = new ArrayList<>();
    public static Set<Object> listaObiektow = new HashSet<>();
    public static Set<Vehicle> listaPojazdow = new HashSet<>();
    public static Set<ParkingSpace> listaMiejscParkingowych = new HashSet<>();
    public static List<IndependentCarServiceSpot> listaMiejscSerwisowych = new LinkedList<>();
    public static int numer;
    public static String text;
    public static boolean naprawa=false;

    public static void main(String[] args) throws TenantAlert, NeverRentException, TooManyThingsException, IOException {
        int select;
        boolean exit = false;

    // TWORZENIE OBIEKTY

    Service s1 = new Service("U Zbycha",1,10,50,3,1);

    ConsumerWarehouse cw1 = new ConsumerWarehouse("ABC",2000,500, LocalDate.of(2021,4,22), LocalDate.of(2021,4,26));
    ConsumerWarehouse cw2 = new ConsumerWarehouse("BCD",300,200, LocalDate.of(2021,2,12), LocalDate.of(2021,7,28));
    ConsumerWarehouse cw3 = new ConsumerWarehouse("DEF",300,10,LocalDate.of(2021,3,11), LocalDate.of(2021,8,1));
    ConsumerWarehouse cw4 = new ConsumerWarehouse("DEF",300,10,LocalDate.of(2021,3,11), LocalDate.of(2021,12,12));
    ConsumerWarehouse cw5 = new ConsumerWarehouse("DEF",300,10,LocalDate.of(2021,3,11), LocalDate.of(2021,11,15));
    ServiceWarehouse sw1 = new ServiceWarehouse("FGH",45,400, LocalDate.of(2021, 3, 26),LocalDate.of(2021,4,27));
    ServiceWarehouse sw2 = new ServiceWarehouse("IJK",30,1250,LocalDate.of(2021, 4, 8),LocalDate.of(2021,5,25));
    ServiceWarehouse sw3 = new ServiceWarehouse("FGH",45,400, LocalDate.of(2021, 3, 26),LocalDate.of(2021,4,27));
    ServiceWarehouse sw4 = new ServiceWarehouse("IJK",30,1250,LocalDate.of(2021, 4, 8),LocalDate.of(2021,5,25));
    ServiceWarehouse sw5 = new ServiceWarehouse("FGH",45,400, LocalDate.of(2021, 3, 26),LocalDate.of(2021,4,27));
    ServiceWarehouse sw6 = new ServiceWarehouse("IJK",30,1250,LocalDate.of(2021, 4, 8),LocalDate.of(2021,5,25));

    Person p1 = new Person("Adam", "Kowalski",132323222,"Warszawa, Gorecka 5",LocalDate.of(2020,6,28));
    Person p2 = new Person("Paweł", "Jarosz",232321232,"Czestochowa, Warynskiego 5",LocalDate.of(2019,4,6));
    Person p3 = new Person("Marek", "Wilusz",023021202,"Warszawa, Mokotowska 5",LocalDate.of(2020,11,11));
    Person p4 = new Person("Adam", "Ozga",1232320202,"Warszawa, Kielecka 25a",LocalDate.of(2021,1,26));
    Person p5 = new Person("Michał", "Browar",523302102,"Raszyn, Warszawska 135",LocalDate.of(2021,3,7));

    ParkingSpace ps1 = new ParkingSpace("Miejsce A",10,200);
    ParkingSpace ps2 = new ParkingSpace("Miejsce B",10,200);
    ParkingSpace ps3 = new ParkingSpace("Miejsce C",7,150);

    Object o1 = new Object("rower",20);
    Object o2 = new Object("pralka",10,5,3);
    Object o3 = new Object("telewizor",179);
    Object o4 = new Object("obraz",2);
    Object o5 = new Object("wiadro",2,2,2);

    IndependentCarServiceSpot Ics1 = new IndependentCarServiceSpot("XYZ",100);
    IndependentCarServiceSpot Ics2 = new IndependentCarServiceSpot("ZYX",20);
    IndependentCarServiceSpot Ics3 = new IndependentCarServiceSpot("YZX",100);

    Motorcycle m1 = new Motorcycle("Ducati","scigacz",1000,234);
    Amphibian a1 = new Amphibian("ISUZU","wojskowy",7000,2);
    CityCar c1 = new CityCar("Toyota","miejskie",1200,"automatyczna");
    OffRoadCar of1 = new OffRoadCar("Nissan","Offroadowy",4200,"4x4");

    // DODAWANIE POMIESZCZEN, MIEJSC NAPRAWCZYCH

     pomieszczeniaMagazynow = s1.getPomieszczeniaMagazynowe();
     pomieszczeniaSerwisow = s1.getPomieszczeniaSerwisowowe();
     miejscaNaprawcze = s1.getMiejscaNaprawcze();
     miejscaSerwisowe = s1.getMiejscaSerwisowe();

    dodajPomieszczenieMagazynowe(cw1);
    dodajPomieszczenieMagazynowe(cw2);
    dodajPomieszczenieMagazynowe(cw3);
    dodajPomieszczenieSerwisowe(sw1);
    dodajPomieszczenieSerwisowe(sw2);

    dodajMiejsceNaprawcze(Ics1);
    dodajMiejsceNaprawcze(Ics2);
    dodajMiejsceNaprawcze(Ics3);

        listaOsob.add(p1);
        listaOsob.add(p2);
        listaOsob.add(p3);
        listaOsob.add(p4);
        listaOsob.add(p5);

        listaMagazynow.add(cw1);
        listaMagazynow.add(sw1);
        listaMagazynow.add(cw2);
        listaMagazynow.add(cw3);
        listaMagazynow.add(sw2);

        listaObiektow.add(o1);
        listaObiektow.add(o2);
        listaObiektow.add(o3);
        listaObiektow.add(o4);
        listaObiektow.add(o5);

        listaPojazdow.add(m1);
        listaPojazdow.add(a1);
        listaPojazdow.add(c1);
        listaPojazdow.add(of1);

        listaMiejscParkingowych.add(ps1);
        listaMiejscParkingowych.add(ps2);
        listaMiejscParkingowych.add(ps3);

        listaMiejscSerwisowych.add(Ics1);
        listaMiejscSerwisowych.add(Ics2);
        listaMiejscSerwisowych.add(Ics3);



    // FUNKCJONALNOSC

        cw1.wynajmijMagazyn(p1);
        cw2.wynajmijMagazyn(p2);
        sw2.wynajmijMagazyn(p2);

        System.out.println();
        cw1.wlozPrzedmiot(p1,o1);
        cw2.wlozPrzedmiot(p2,o5);
        sw1.wlozPrzedmiot(p3,o1);
        cw1.dodajUprawnienie(p2);
        System.out.println();

        Ics1.wynajmijMiejscSerwisowe(p1,13);
        Ics1.zglosPotrzebeSerwisowa(p1,of1,false);
        Ics1.zglosPotrzebeSerwisowa(p1,m1,false);
        Ics1.zglosPotrzebeSerwisowa(p1,a1,false);
        Ics1.zglosPotrzebeSerwisowa(p3,c1,false);
        //System.out.println(Ics1.getListaPojazdowOczekujacych());



        // SYMULACJA CZASU

        MyThread Thr1 = new MyThread();
        MyThread2 Thr2= new MyThread2();
        //Thr1.start();
        //Thr2.start();

        System.out.println();
        System.out.println();

        // MENU

        while(!exit) {
            System.out.println("----Menu-----");
            System.out.println("WYBIERZ OPCJE:");
            System.out.println(" 1 - Wyjscie z programu");
            System.out.println(" 2 - Wybierz osobe");
            System.out.println(" 3 - Wyswietl informacje");
            System.out.println(" 4 - Wyswietl wolne pomieszczenia");
            System.out.println(" 5 - Wynajmij pomieszczenie");
            System.out.println(" 6 - Sprawdz zawartosc pomieszczenia");
            System.out.println(" 7 - Wloz przedmiot do magazynu");
            System.out.println(" 8 - Zaparkuj pojazd");
            System.out.println(" 9 - Zabierz przedmiot z magazynu");
            System.out.println(" 10 - Zwolnij miejsce z parkingu");
            System.out.println(" 11 - Rozpocznij zgłoszenie serwisowe [Rozpocznij naprawe]");
            System.out.println(" 12 - Zapisz aktualny stan");

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
                    if(osoba==null){
                        System.out.println("Najpierw wybierz Osobe");
                        wybierzOsobe(sc);
                    }
                    wyswietlInformacje();
                    break;
                case 4:
                    System.out.println("Lista wolnych magazynow");
                    System.out.println(listaWolnychMagazynow);
                    break;

                case 5:
                    if(osoba==null){
                        System.out.println("Najpierw wybierz Osobe");
                        wybierzOsobe(sc);
                    }
                    System.out.println("Wybierz magazyn");
                    System.out.println(listaWolnychMagazynow);
                    numer = sc.nextInt();
                    listaWolnychMagazynow.forEach(
                            W -> {
                                if (W.getId() == numer) {
                                    warehouse = W;
                                }
                            }

                    );

                warehouse.wynajmijMagazyn(osoba);
                break;
             case 6:
                if(osoba==null) {
                System.out.println("Najpierw wybierz Osobe");
                wybierzOsobe(sc);
                 }

                if(osoba.getListaPomieszczen().isEmpty()){
                System.out.println("Osoba nic nie wynajmuje");
                }else{
                    wyswietlInformacje();
                     System.out.println("Wybierz id magazynu by zobaczyc jego zawartosc");
                    numer = sc.nextInt();
                    listaMagazynow.forEach(
                        M-> {
                            if (M.getId() == numer) {
                                warehouse = M;
                                warehouse.przedmiotyMagazynu();
                            }
                        }
                );
            }
                break;
            case 7:
                if(osoba==null){
                    System.out.println("Wybierz osobe");
                    wybierzOsobe(sc);
                }
                System.out.println("Wybierz magazyn do ktorego chcesz wlozyc przedmiot");
                wybierzMagazyn(sc);
                System.out.println("Wybierz przedmiot");
                wybierzPrzedmiot(sc);
                warehouse.wlozPrzedmiot(osoba,obiekt);
                break;
            case 8:
                if(osoba==null){
                    System.out.println("Wybierz osobe");
                    wybierzOsobe(sc);
                }
                System.out.println("Wybierz Miejsce parkingowe");
                wybierzMiejsceParkingowe(sc);
                System.out.println("Wybierz magazyn");
                wybierzMagazyn(sc);
                System.out.println("Wybierz pojazd");
                wybierzPojazd(sc);
                System.out.println("Podaj ilosc dni najmu");
                numer = sc.nextInt();
                miejsceParkingowe.wynajmijMiejsceParkingowe(osoba,warehouse,numer);
                miejsceParkingowe.dodajPojazd(pojazd);
                break;
            case 9:
            if(osoba==null){
                System.out.println("Wybierz osobe");
                wybierzOsobe(sc);
            }
                System.out.println("Wybierz magazyn");
                wybierzMagazyn(sc);
                System.out.println("Wybierz przedmiot");
                warehouse.przedmiotyMagazynu();
                numer = sc.nextInt();
                listaObiektow.forEach(
                        O -> {
                            if(O.getID()==numer)
                                obiekt = O;

                        }
                );
                warehouse.wyjmijPrzedmiot(osoba,obiekt);
                break;
            case 10:
            if(miejsceParkingowe==null){
                System.out.println("Wybierz miejsce parkingowe");
                wybierzMiejsceParkingowe(sc);
            }
                miejsceParkingowe.usunPojazd();
                break;
            case 11:
                if(osoba==null){
                    System.out.println("Wybierz osobe");
                    wybierzOsobe(sc);
                }
                System.out.println("Wybierz serwis samochodowy");
                wybierzSerwisSamochodowy(sc);
                System.out.println("Wybierz pojazd");
                wybierzPojazd(sc);
                System.out.println("Czy naprawa ma byc samodzielna? T-TAK / N-NIE");
                text = sc.next();
                if(text.equals("T")||text.equals("t")){
                    naprawa=true;
                }else {
                    naprawa=false;
                }
                serwis.zglosPotrzebeSerwisowa(osoba,pojazd,naprawa);
                break;
            case 12:
                File file = new File("resources/warehouses.txt");
                file.createNewFile();
                PrintWriter writer = new PrintWriter(file);
                writer.println(s1);
                writer.println("Lista Pomieszczen: " + listaMagazynow);
                stanMagazynow();
                writer.println("Wolne magazyny: " +listaWolnychMagazynow);
                writer.println("Zajete magazyny: "+listaZajetychMagazynow);
                writer.println("Lista osob: " +listaOsob);
                writer.println("Lista przedmiotow: " +listaObiektow);
                writer.println();
                writer.println("Osoby wynajmujace:");
                for(Warehouse w: listaMagazynow){
                    writer.println(w.Najemcy());
                }
                writer.println();
                writer.println("Przedmioty w magazynach:");
                for ( Warehouse l: listaMagazynow) {
                   writer.println(l.przedmiotyMagazynu());
                }
                writer.println();
                writer.close();

                File file2 =  new File("resources/services.txt");
                file2.createNewFile();
                PrintWriter write = new PrintWriter(file2);
                write.println(s1);
                write.println("Pojazdy: " +listaPojazdow);
                write.println("Miejsca parkingowe " +listaMiejscParkingowych);
                write.println("Miejsca serwisowe: "+listaMiejscSerwisowych);
                write.println();
                write.println("Lista pojazdow naprawianych: ");
                for(IndependentCarServiceSpot i: listaMiejscSerwisowych){
                    write.println(i.getListaNaprawianych());
                }
                write.println();
                write.println("Lista pojazdow oczekujacych: ");
                for(IndependentCarServiceSpot i: listaMiejscSerwisowych){
                    write.println(i.getKolejkaOczekujacych());
                }
                write.println();
                write.println("Historia napraw: ");
                for(IndependentCarServiceSpot i: listaMiejscSerwisowych){
                    write.println(i.getHistoriaNapraw());
                }
                write.close();
                break;
                default:
                    System.out.println("Prosze podac numer opcji zgodny z MENU");
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

        System.out.println("Wybrana osoba: " + osoba);
    }

    private static void wybierzMagazyn(Scanner sc) {
        System.out.println(listaMagazynow);
        numer = sc.nextInt();

       listaMagazynow.forEach(
               M -> {
                   if(M.getId() == numer)
                       warehouse=M;
               }
       );
    }

    private static void wybierzPrzedmiot(Scanner sc){
        System.out.println(listaObiektow);
        numer = sc.nextInt();
        listaObiektow.forEach(
                O -> {
                    if(O.getID()==numer)
                        obiekt = O;

                }

        );
    }

    private static void wybierzPojazd(Scanner sc){
        System.out.println(listaPojazdow);
        numer = sc.nextInt();
        listaPojazdow.forEach(
                V -> {
                    if(V.getIDpojazdu()==numer)
                       pojazd = V;

                }

        );
    }

    private static void wybierzSerwisSamochodowy(Scanner sc){
        System.out.println(listaMiejscSerwisowych);
        numer = sc.nextInt();
        listaMiejscSerwisowych.forEach(
                LMP -> {
                    if(LMP.getID()==numer)
                        serwis = LMP;
                }


        );
    }

    private static void wybierzMiejsceParkingowe(Scanner sc){
        System.out.println(listaMiejscParkingowych);
        numer = sc.nextInt();
        listaMiejscParkingowych.forEach(
                L -> {
                    if(L.getID()==numer)
                        miejsceParkingowe = L;

                }

        );
    }

    public static void dodajPomieszczenieMagazynowe(ConsumerWarehouse cw){
        pomieszczeniaMagazynow[iloscObiektowM++] = cw;
    }

    public static void dodajPomieszczenieSerwisowe(ServiceWarehouse sw){
        pomieszczeniaSerwisow[iloscObiektowS++] = sw;
    }

    public static void dodajMiejsceNaprawcze(IndependentCarServiceSpot css){
        miejscaNaprawcze[iloscMiejscN++]=css;
    }

    public static void dodajMiejsceSerwisowe(IndependentCarServiceSpot Icss){
        miejscaSerwisowe[iloscMiejscS++]=Icss;
    }

    public static void wyswietlStanPomieszczen(Warehouse[] w){

        for(Warehouse c: w){
            if(c==null){
                System.out.println("Brak pomieszczenia");
            }else
                System.out.println(c);
        }

    }

    public static void wyswietlInformacje(){
        System.out.println(osoba);
        osoba.getListaPomieszczen().forEach( info ->{
            System.out.println(info);
        });
    }

    public static void stanMagazynow(){
        for (int i = 0; i < listaMagazynow.size(); i++) {
            if(listaMagazynow.get(i).isCzyZajety()){
                listaZajetychMagazynow.add(listaMagazynow.get(i));
            }else {
                    listaWolnychMagazynow.add(listaMagazynow.get(i));
            }
        }
    }


}
