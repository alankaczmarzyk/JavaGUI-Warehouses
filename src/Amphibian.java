public class Amphibian extends Vehicle{

    private int iloscGasienic;
    private int ID;

    public Amphibian(String marka, String typPojazdu, double pojemnoscSilnika, int iloscGasienic) {
        super(marka, typPojazdu, pojemnoscSilnika);
        this.iloscGasienic = iloscGasienic;

        ID=IDpojazdu++;
    }

    @Override
    int getIDpojazdu() {
        return this.ID;
    }

    public String toString() {
        return super.toString() + this.ID;
    }
}
