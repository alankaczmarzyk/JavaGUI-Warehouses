import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class Person {

    private String imie;
    private String nazwisko;
    private int PESEL;
    private String adresZamieszkania;
    private LocalDate pierwszyNajem;
    private int id;
    private static int IDosoby =1;
    private static List<Warehouse> listaPomieszczen= new ArrayList<>();


    public Person(String imie, String nazwisko, int PESEL, String adresZamieszkania, LocalDate pierwszyNajem) throws NeverRentException {
        this.imie=imie;
        this.nazwisko=nazwisko;
        this.PESEL=PESEL;
        this.adresZamieszkania=adresZamieszkania;
        this.pierwszyNajem=pierwszyNajem;

       id = IDosoby++;

       if(pierwszyNajem==null){
           throw new NeverRentException(this + " " +"Osoba nigdy nie wynajela pomieszczenia");
       }else {

       }

    }

    public List<Warehouse> getListaPomieszczen() {
        return listaPomieszczen;
    }

    public int getId() {
        return id;
    }

    public void dodajPomieszczenie(Warehouse w){
        listaPomieszczen.add(w);
    }

    public String toString(){

        return "Osoba: "+this.imie + " " + this.nazwisko + " ID: " + this.id;
    }





}
