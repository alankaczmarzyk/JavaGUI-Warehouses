import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Person {

    public String imie;
    public String nazwisko;
    private int PESEL;
    private String adresZamieszkania;
    private LocalDate pierwszyNajem;
    private int id;
    private static int IDosoby =1;
    private List<Warehouse> listaPomieszczenWynajetych = new ArrayList<>();
    private static List<Person> listaOsob;


    public Person(String imie, String nazwisko, int PESEL, String adresZamieszkania, LocalDate pierwszyNajem) throws NeverRentException {
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.PESEL=PESEL;
        this.adresZamieszkania=adresZamieszkania;
        this.pierwszyNajem=pierwszyNajem;

       id = IDosoby++;

       dodajosoby();

    }

    public void dodajosoby(){

        if(listaOsob==null){
            listaOsob = new LinkedList<>();
            listaOsob.add(this);
        }else {
            listaOsob.add(this);
        }

    }

    public static List<Person> getListaOsob(){
        return listaOsob;
    }

    public List<Warehouse> listaPomieszczenWynajetych() {
        return listaPomieszczenWynajetych;
    }

    public int getId() {
        return id;
    }

    public void dodajPomieszczenie(Warehouse w){
        listaPomieszczenWynajetych.add(w);
    }

    public String toString(){

        return "Osoba: "+this.imie + " " + this.nazwisko + " ID: " + this.id;
    }





}
