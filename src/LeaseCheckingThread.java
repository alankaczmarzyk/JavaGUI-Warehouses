import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class LeaseCheckingThread extends Thread{
    public static List<Warehouse> listaZajetychMagazynow = Main.getListaZajetychMagazynow();
    public static List<Warehouse> listaPrzedawnionych = new LinkedList<>();
    public static LocalDate dzien;
    private boolean znaleziony=false;

    @Override
    public void run() {

        while (!isInterrupted()) {
            dzien = TimeLapseThread.getDzien();

            if (listakoncowa()) {
                this.interrupt();
                try {
                    throw new TenantAlert("Nie ma juz wiecej najmow. Watek zatrzymany.");
                } catch (TenantAlert t) {
                    System.out.println(t.getMessage());
                }
                break;
            }

            try {
                listaZajetychMagazynow.forEach(
                        L -> {
                            if ((((dzien.isAfter(L.getDataZakonczenia())) || (dzien.isEqual(L.getDataZakonczenia()))) && (!listaPrzedawnionych.contains(L)))) {
                                znaleziony=true;
                                listaPrzedawnionych.add(L);
                                try {
                                    throw new TenantAlert(L + ": Najem tego magazynu sie przedawnil.");
                                } catch (TenantAlert t) {
                                    System.out.println(t.getMessage());
                                }
                            }
                        });
                if(!znaleziony && !listaPrzedawnionych.isEmpty())
                    System.out.println("Brak przedawnionych magazynow w tym czasie.");
                else {
                    if (!listaPrzedawnionych.isEmpty()) {
                        System.out.println("Lista magazynow przedawnionych:");
                        System.out.println(listaPrzedawnionych);
                    }
                }
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            znaleziony=false;
        }
    }

    public synchronized boolean listakoncowa(){
        if(listaPrzedawnionych.size() == listaZajetychMagazynow.size()) return true;
        else return false;
    }

}











