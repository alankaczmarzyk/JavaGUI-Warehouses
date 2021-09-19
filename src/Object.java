import java.util.HashSet;
import java.util.Set;

public class Object {

    private String nazwa;
    private double polePowierzchni;
    private static int IDObiektu = 1;
    private int ID;

    public Object(String nazwa, double polePowierzchni){
        this.nazwa=nazwa;
        this.polePowierzchni=polePowierzchni;

        ID=IDObiektu++;
    }

    public Object(String nazwa, double dlugosc, double szerokosc, double wysokosc){
        this.nazwa=nazwa;
        this.polePowierzchni=(2*dlugosc*szerokosc)+(2*wysokosc*szerokosc)+(2*dlugosc*wysokosc);

        ID=IDObiektu++;
    }

    public double getPolePowierzchni() {
        return polePowierzchni;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return "Obiekt: "+this.nazwa + ", ID:"+ this.ID + ", rozmiar: "+this.polePowierzchni;
    }
}
