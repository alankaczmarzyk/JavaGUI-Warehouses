import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

abstract class CarServiceSpot {

    private String nazwa;
    private double powierzchnia;
    protected  HashMap<IndependentCarServiceSpot, Set<Vehicle>> historiaNapraw= new HashMap<>();

    public CarServiceSpot(String nazwa, double powierzchnia){
        this.nazwa = nazwa;
        this.powierzchnia = powierzchnia;

    }

    abstract public void wykonajNaprawe(Vehicle v, double Cena);

    abstract public void zakonczNaprawe(Vehicle v);


   abstract void zglosPotrzebeSerwisowa(Person p,Vehicle vec, boolean czyNaprawaSamodzielna);

    @Override
    public String toString() {
        return "MiejsceSerwisowe=" +
                "nazwa='" + nazwa + '\'';
    }
}



