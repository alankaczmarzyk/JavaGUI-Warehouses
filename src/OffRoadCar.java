public class OffRoadCar extends Vehicle{

    private String naped;
    private int ID;

    public OffRoadCar(String marka, String typPojazdu, double pojemnoscSilnika, String naped) {
        super(marka, typPojazdu, pojemnoscSilnika);
        this.naped = naped;

        ID = IDpojazdu++;
    }

    @Override
    int getIDpojazdu() {
        return this.ID;
    }

    @Override
    public String toString() {
        return super.toString() + this.ID;
    }
}
