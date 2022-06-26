import java.time.LocalDate;

public class TimeLapseThread extends Thread {
    private static LocalDate day = LocalDate.now();

    @Override
    public void run() {
        System.out.println();
        while (!isInterrupted()) {
            if (day.isEqual(LocalDate.of(2022, 12, 31))) {
                this.interrupt();
                try {
                    throw new TenantAlert("Koniec roku. Watek zatrzymany.");
                } catch (TenantAlert t) {
                    System.out.println(t.getMessage());
                }
                break;
            }

            try {
                day = day.plusDays(1);
                System.out.println(day);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static LocalDate getDay() {
        return day;
    }
}
