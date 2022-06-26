import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class LeaseCheckingThread extends Thread{
    public static List<Warehouse> occupiedWarehouses = Main.getOccupiedWarehouseList();
    public static List<Warehouse> expiredWarehouses = new LinkedList<>();
    public static LocalDate day;
    private boolean found =false;

    @Override
    public void run() {

        while (!isInterrupted()) {
            day = TimeLapseThread.getDay();

            if (finalList()) {
                this.interrupt();
                try {
                    throw new TenantAlert("Nie ma juz wiecej najmow. Watek zatrzymany.");
                } catch (TenantAlert t) {
                    System.out.println(t.getMessage());
                }
                break;
            }

            try {
                occupiedWarehouses.forEach(
                        L -> {
                            if ((((day.isAfter(L.getFinishDateOfLease())) || (day.isEqual(L.getFinishDateOfLease()))) && (!expiredWarehouses.contains(L)))) {
                                found =true;
                                expiredWarehouses.add(L);
                                try {
                                    throw new TenantAlert(L + ": Najem tego magazynu sie przedawnil.");
                                } catch (TenantAlert t) {
                                    System.out.println(t.getMessage());
                                }
                            }
                        });
                if(!found && !expiredWarehouses.isEmpty())
                    System.out.println("Brak przedawnionych magazynow w tym czasie.");
                else {
                    if (!expiredWarehouses.isEmpty()) {
                        System.out.println("Lista magazynow przedawnionych:");
                        System.out.println(expiredWarehouses);
                    }
                }
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            found =false;
        }
    }

    public synchronized boolean finalList(){
        if(expiredWarehouses.size() == occupiedWarehouses.size()) return true;
        else return false;
    }

}











