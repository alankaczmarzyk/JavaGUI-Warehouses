public class CityCar extends Vehicle{

    private String skrzyniaBiegow;
    private int ID;

    public CityCar(String marka, String typPojazdu, double pojemnoscSilnika, String skrzyniaBiegow) {
        super(marka, typPojazdu, pojemnoscSilnika);
        this.skrzyniaBiegow = skrzyniaBiegow;

        ID = IDpojazdu++;
    }


    @Override
    int getIDpojazdu() {
        return this.ID;
    }

    public String toString() {
        return super.toString() + this.ID;
    }
}
