import java.util.ArrayList;
import java.util.List;

public class Service {

    private int serwisID;
    private String nazwa;
    private int liczbaPowierzchni;
    private int procentMagazynu;
    public static int liczbaMiejscNaprawczych;
    private static int iloscMiejscSerwisowych=1;
    public ConsumerWarehouse[] pomieszczeniaMagazynowe;
    public ServiceWarehouse[] pomieszczeniaSerwisowowe;
    public IndependentCarServiceSpot[] miejscaNaprawcze;
    public IndependentCarServiceSpot[] miejscaSerwisowe;

    public Service(String nazwa, int serwisID, int liczbaPowierzchni, int procentMagazynu, int liczbaMiejscNaprawczych, int iloscMiejscSerwisowych){

        this.nazwa=nazwa;
        this.serwisID=serwisID;
        this.liczbaPowierzchni=liczbaPowierzchni;
        this.procentMagazynu=procentMagazynu;
        this.iloscMiejscSerwisowych=iloscMiejscSerwisowych;
        this.liczbaMiejscNaprawczych=liczbaMiejscNaprawczych;

        pomieszczeniaMagazynowe = new ConsumerWarehouse[liczbaPowierzchni*procentMagazynu/100];
        pomieszczeniaSerwisowowe = new ServiceWarehouse[liczbaPowierzchni-(liczbaPowierzchni*procentMagazynu/100)];
        miejscaNaprawcze = new IndependentCarServiceSpot[liczbaMiejscNaprawczych];
        miejscaSerwisowe = new IndependentCarServiceSpot[iloscMiejscSerwisowych];

    }


    public ConsumerWarehouse[] getPomieszczeniaMagazynowe(){
        return pomieszczeniaMagazynowe;
    }

    public  ServiceWarehouse[] getPomieszczeniaSerwisowowe() {
        return pomieszczeniaSerwisowowe;
    }

    public IndependentCarServiceSpot[] getMiejscaNaprawcze(){
        return miejscaNaprawcze;
   }

    public IndependentCarServiceSpot[] getMiejscaSerwisowe(){
        return miejscaSerwisowe;
    }

    public static int getLiczbaMiejscNaprawczych() {
        return liczbaMiejscNaprawczych;
    }

    public String toString(){
        return "Serwis:'" + this.nazwa +"', ID: " + this.serwisID + ", Liczba powierzchni: " + this.liczbaPowierzchni + ", Magazyn: " + this.procentMagazynu +"%";
    }

}
