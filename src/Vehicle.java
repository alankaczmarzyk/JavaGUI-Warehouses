abstract public class Vehicle {

    private String marka;
    private String typPojazdu;
    private double pojemnoscSilnika;
    public static int IDpojazdu=1;

    public Vehicle(String marka, String typPojazdu, double pojemnoscSilnika){
        this.marka = marka;
        this.typPojazdu = typPojazdu;
        this.pojemnoscSilnika = pojemnoscSilnika;

    }


    abstract int getIDpojazdu();

    @Override
    public String toString() {
        return "Pojazd: " +
                "marka='" + marka + '\'' +
                ", typPojazdu='" + typPojazdu + '\'' +
                ", ID=";

    }
}
