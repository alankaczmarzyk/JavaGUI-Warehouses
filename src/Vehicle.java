import java.util.HashSet;
import java.util.Set;

abstract public class Vehicle {

    private String marka;
    private String typPojazdu;
    private double pojemnoscSilnika;
    public static int IDpojazdu=1;
    public static Set<Vehicle> listaPojazdow = new HashSet<>();

    public Vehicle(String marka, String typPojazdu, double pojemnoscSilnika){
        this.marka = marka;
        this.typPojazdu = typPojazdu;
        this.pojemnoscSilnika = pojemnoscSilnika;

        listaPojazdow.add(this);
    }



    abstract int getIDpojazdu();

    @Override
    public String toString() {
        return "Pojazd: " +
                "marka='" + marka + '\'' +
                ", ID=";

    }

    public static Set<Vehicle> getListaPojazdow() {
        return listaPojazdow;
    }
}
