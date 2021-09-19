import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MyThread extends Thread{

    public static LocalDate dzien = LocalDate.now();
    private boolean wyswietl;
    private Warehouse magazyn=null;
    private Main main;
    private static List<Warehouse> lista = Main.listaZajetychMagazynow;


    private synchronized boolean running(){
        return this.wyswietl=true;
    }



    @Override
    public void run(){
        while(running()){
            dzien = dzien.plusDays(1);
            System.out.println(dzien);
            try{
                Thread.sleep(5000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static LocalDate getDzien() {
        return dzien;
    }


}
