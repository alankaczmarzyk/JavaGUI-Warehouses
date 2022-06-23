import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class TimeLapseThread extends Thread {
    private static LocalDate dzien = LocalDate.now();

    @Override
    public void run() {
        System.out.println();
        while (!isInterrupted()) {
            if (dzien.isEqual(LocalDate.of(2022, 12, 31))) {
                this.interrupt();
                try {
                    throw new TenantAlert("Koniec roku. Watek zatrzymany.");
                } catch (TenantAlert t) {
                    System.out.println(t.getMessage());
                }
                break;
            }

            try {
                dzien = dzien.plusDays(1);
                System.out.println(dzien);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static LocalDate getDzien() {
        return dzien;
    }
}
