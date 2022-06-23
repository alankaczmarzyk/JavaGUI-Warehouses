import java.util.*;

public class IndependentCarServiceSpot extends CarService{
    private int id;
    private static int counter=0;
    private Person[] wlasciciel = new Person[1];
    private Vehicle[] miejscaSerwisowe= new Vehicle[1];
    private static IndependentCarServiceSpot[] listaMiejscSerwisowych;
    private Set<Vehicle> listaPojazdowSerwisowanych = new HashSet<>();
    private  HashMap<Person, Vehicle> osobyiPojazdy = new HashMap<>();
    private static Map<IndependentCarServiceSpot, Vehicle> listaWszystkichPojazdowSerwisowanych = new HashMap<>();
    private  Queue<Vehicle> kolejkaOczekujacychPojazdow = new LinkedList<>();;
    private Queue<Person> kolejkaWlascicieli = new LinkedList<>();
    private boolean czyObecnieNaprawiany =false;
    private Vehicle obecnyPojazd;
    private boolean czyWlasciciel=false;
    private boolean czyMoznaZaczacNaprawe=false;
    private String nowaNazwa;
    private boolean czyWynajmuje=false;

    public IndependentCarServiceSpot(String nazwa, double powierzchnia) {
        super(nazwa, powierzchnia);
        nowaNazwa=nazwa;
        id = IDspot++;
        dodajMiejsce();
    }

    public Queue<Vehicle> getKolejkaOczekujacychPojazdow() {
        return kolejkaOczekujacychPojazdow;
    }

    public void ropozcznijSamodzielnaNaprawe(Person p,Vehicle v) {

            if(osobyiPojazdy.containsValue(v)) {
                Person osoba = p;
                Vehicle pojazd = v;
                for(Map.Entry<Person, Vehicle> os : osobyiPojazdy.entrySet()){
                    if(os.getKey().equals(p) && os.getValue().equals(v)){
                        czyWlasciciel=true;
                    }
                }
                if(!czyWlasciciel){
                    System.out.println("Tylko wlasciciel moze rozpoczac prace serwisowa przy pojezdzie.");
                }else {
                    if (!czyObecnieNaprawiany) {
                        obecnyPojazd = v;
                        listaPojazdowSerwisowanych.add(v);
                        listaWszystkichPojazdowSerwisowanych.put(this, v);
                        historiaNapraw.put(this, listaPojazdowSerwisowanych);
                        System.out.println("Wlasciciel pojazdu rozpoczal serwisowanie.");
                        czyObecnieNaprawiany = true;
                    } else {
                        if (kolejkaOczekujacychPojazdow.contains(v)) {
                            czyMoznaZaczacNaprawe=true;
                            System.out.println("Ten pojazd znajduje sie w kolejce oczekujacych na serwis.");

                        } else if (obecnyPojazd == v) {
                            System.out.println("Ten pojazd jest juz w serwisie.");
                        } else {
                        }

                    }
                    czyWlasciciel=false;
                }
            }else {
                System.out.println("Najpierw wynajmij miejsce dla tego pojazdu.");
            }
    }

    public void zakonczSamodzielnaNaprawe(Person p) {

        if(p==wlasciciel[0]) {
            if (listaPojazdowSerwisowanych.contains(obecnyPojazd)) {
                listaPojazdowSerwisowanych.remove(obecnyPojazd);
               listaWszystkichPojazdowSerwisowanych.remove(this);
                if(czyMoznaZaczacNaprawe) {
                    System.out.println("Wlasciciel zakonczyl naprawe serwisowa: " +obecnyPojazd + " Jego miejsce zastapil: " + kolejkaOczekujacychPojazdow.element());
                    Vehicle thisVehicle = kolejkaOczekujacychPojazdow.poll();
                    listaPojazdowSerwisowanych.add(thisVehicle);
                    listaWszystkichPojazdowSerwisowanych.put(this,thisVehicle);
                    wlasciciel[0] = kolejkaWlascicieli.poll();
                }else {
                    System.out.println("Wlasciciel zakonczyl naprawe serwisowa.");
                    czyZajete=false;
                    czyObecnieNaprawiany=false;
                }
            }
        }else {
            System.out.println("Tylko wlasciciel moze zakonczyc swoja prace.");
        }
    }



    @Override
    public void dodajMiejsce() {

        int count = Service.getIloscMiejscSerwisowych();

        if (listaMiejscSerwisowych == null) {
            listaMiejscSerwisowych = new IndependentCarServiceSpot[count];
            listaMiejscSerwisowych[counter++] = this;
            wszystkieMiejsca.add(this);
        } else {
            try {
                listaMiejscSerwisowych[counter++] = this;
                wszystkieMiejsca.add(this);
            }catch (ArrayIndexOutOfBoundsException e) {
                throw new IndexOutOfBoundsException("Przekroczono maksymalna ilosc miejsc serwisowych.");

            }
        }

    }

    public static IndependentCarServiceSpot[] getListaMiejscSerwisowych() {
        return listaMiejscSerwisowych;
    }

    @Override
    void wynajmijMiejsce(Person p, Vehicle vec) {

        if(!czyZajete){
            wlasciciel[0] = p;
            osobyiPojazdy.put(p, vec);
            czyZajete = true;
            System.out.println("Miejsce serwisowe "+nowaNazwa+" zostalo wynajete.");
        } else{
            System.out.println("Miejsce serwisowe jest juz zarezerwowane. Czekasz w kolejce.");
            kolejkaOczekujacychPojazdow.add(vec);
            osobyiPojazdy.put(p,vec);
        }
    }

    public boolean czyWlasnieWynajmuje(Person p){
        if(p==wlasciciel[0]){
            czyWynajmuje =true;
        }else {
            czyWynajmuje =false;
        }
        return czyWynajmuje;
    }

    @Override
    public int getId() {
        return id;
    }

    public static Map<IndependentCarServiceSpot, Vehicle> getListaSerwisowanychPojazdow() {
        return listaWszystkichPojazdowSerwisowanych;
    }

    public static Map<IndependentCarServiceSpot, Vehicle> getListaWszystkichPojazdowSerwisowanych() {
        return listaWszystkichPojazdowSerwisowanych;
    }

    @Override
    public String toString(){
        return super.toString()+ " [Miejsce Serwisowe]" + " ID:"+ id + " stan: "+(czyWlasnieWynajmuje(Main.getOsoba())? "Wynajety przez Ciebie": czyZajete? "Zajete": "Wolne");
    }

}
