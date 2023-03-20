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
        MainMenu.blockViev();
        while (!isInterrupted()) {
            day = TimeLapseThread.getDay();

            if(occupiedWarehouses.isEmpty()){
                MainMenu.getTextArea().setText("Brak wynajetych magazynow.\n");
                return;
            }

            if (finalList()) {
                this.interrupt();
                try {
                    throw new TenantAlert("Nie ma juz wiecej najmow.\n");
                } catch (TenantAlert t) {
                    MainMenu.getTextArea().setText(t.getMessage());
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
                                    throw new TenantAlert(L + ": Najem tego magazynu sie przedawnil.\n\n");
                                } catch (TenantAlert t) {
                                    MainMenu.getTextArea().setText(t.getMessage());
                                }
                            }
                        });
                if(!found && !expiredWarehouses.isEmpty())
                {
                    MainMenu.getTextArea().append("Sprawdzam daty zakonczenia najmów..\n");
                }
                else {
                    if (!expiredWarehouses.isEmpty()) {
                        MainMenu.getTextArea().append("Lista magazynow przedawnionych:\n");
                        MainMenu.getTextArea().append(""+expiredWarehouses+"\n\n");
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











