package Warehouse.app.Thread;

import Warehouse.app.Menu.MainMenu;
import Warehouse.app.Exception.TenantAlert;

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
                    throw new TenantAlert("Koniec roku.\n");
                } catch (TenantAlert t) {
                    MainMenu.getTextArea().append(t.getMessage());
                }
                break;
            }

            try {
                day = day.plusDays(1);
                MainMenu.getTextArea().append(""+day+"\n");
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
