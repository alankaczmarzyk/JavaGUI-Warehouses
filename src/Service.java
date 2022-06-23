public class Service {

    private int serwisID;
    private String nazwa;
    private int liczbaPowierzchni;
    private int procentMagazynu;
    public static int liczbaMiejscNaprawczych;
    public static int iloscMiejscSerwisowych;
    private static int iloscPomieszczenMagazynowych;
    private static int iloscPomieszczenSerwisowych;
    public CarServiceSpot[] miejscaNaprawcze;
    public CarServiceSpot[] miejscaSerwisowe;

    public Service(String nazwa, int serwisID, int liczbaPowierzchni, int procentMagazynu, int liczbaMiejscNaprawczych, int iloscMiejscSerwisowych){

        this.nazwa=nazwa;
        this.serwisID=serwisID;
        this.liczbaPowierzchni=liczbaPowierzchni;
        this.procentMagazynu=procentMagazynu;
        this.iloscMiejscSerwisowych=iloscMiejscSerwisowych;
        this.liczbaMiejscNaprawczych=liczbaMiejscNaprawczych;


        iloscPomieszczenMagazynowych = liczbaPowierzchni*procentMagazynu/100;
        iloscPomieszczenSerwisowych = liczbaPowierzchni-(liczbaPowierzchni*procentMagazynu/100);
        miejscaNaprawcze = new CarServiceSpot[liczbaMiejscNaprawczych];
        miejscaSerwisowe = new CarServiceSpot[iloscMiejscSerwisowych];

    }


    public static int getPomieszczeniaMagazynowe(){
        return iloscPomieszczenMagazynowych;
    }

    public static int getPomieszczeniaSerwisowowe() {
        return iloscPomieszczenSerwisowych;
    }

    public CarServiceSpot[] getMiejscaNaprawcze(){
        return miejscaNaprawcze;
   }

    public CarServiceSpot[] getMiejscaSerwisowe(){
        return miejscaSerwisowe;
    }

    public static int getLiczbaMiejscNaprawczych() {
        return liczbaMiejscNaprawczych;
    }

    public static int getIloscMiejscSerwisowych(){return iloscMiejscSerwisowych;}

    public String toString(){
        return "Serwis:'" + this.nazwa +"', ID: " + this.serwisID + ", Liczba powierzchni: " + this.liczbaPowierzchni + ", Magazyn stanowi: " + this.procentMagazynu +"% serwisu";
    }

}
