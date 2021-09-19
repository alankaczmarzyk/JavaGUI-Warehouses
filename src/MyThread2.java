import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class MyThread2 extends Thread{

    private boolean wyswietl;
    private Warehouse magazyn=null;
    private MyThread m1;
    private static List<Warehouse> listaM = Main.listaZajetychMagazynow;
    public static Set<Warehouse> listaPrzedawnionych = new HashSet<>();

    private synchronized boolean running() {
        return this.wyswietl=true;
    }

    @Override
    public void run() {
        while (running()) {
            listaM.forEach(
                    L -> {
                        if (m1.getDzien().isAfter(L.getDataZakonczenia()))
                            magazyn = L;
                        listaPrzedawnionych.add(magazyn);
                        try {
                            throw new TenantAlert("najem sie przedawnil");
                        } catch (TenantAlert tenantAlert) {
                            tenantAlert.printStackTrace();
                        }
                    }
            );

            if (magazyn != null) {
                System.out.println("Wynajem sie skonczyl: "+ listaPrzedawnionych );
            }
            magazyn = null;

            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
