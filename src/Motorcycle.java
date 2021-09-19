public class Motorcycle extends Vehicle{

    private int iloscKoniMechanicznych;
    private int ID;

    public Motorcycle(String marka, String typPojazdu, double pojemnoscSilnika, int iloscKoniMechanicznych) {
        super(marka, typPojazdu, pojemnoscSilnika);
        this.iloscKoniMechanicznych = iloscKoniMechanicznych;

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
