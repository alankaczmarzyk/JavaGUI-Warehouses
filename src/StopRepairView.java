import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StopRepairView {
    public StopRepairView(){
        MainMenu.unlockViev();
        ublockAllButons(false);
        MainMenu.getTextArea().setText("Jaki rodzaj naprawy chcesz zakonczyc?");
        MainMenu.jTextField.setBounds(415,650,0,0);
        MainMenu.buttonOK.setBounds(415,650,0,0);

        JButton newOkButton = new JButton("Serwisowa naprawa");
        newOkButton.setBounds(415,650,275,50);
        newOkButton.setFocusPainted(false);
        MainMenu.frame.add(newOkButton);


        JButton buttonNO = new JButton("Samodzielna naprawa");
        buttonNO.setBounds(690,650,275,50);
        buttonNO.setFocusPainted(false);
        MainMenu.frame.add(buttonNO);
        buttonNO.setOpaque(true);
        buttonNO.setOpaque(true);

        newOkButton.addActionListener(e -> {
            ublockAllButons(true);
            Map<CarServiceSpot, Vehicle> lista = CarServiceSpot.getAllRepairedVehicles();
            MainMenu.getTextArea().setText("Lista napraw serwisowych:\n\n ");
            MainMenu.getTextArea().append(""+lista);
            if (!lista.isEmpty()) {
                MainMenu.getTextArea().setText("Wybierz miejsce serwisowe:");
                MainMenu.getTextArea().append(""+lista);
                ublockAllButons(true);
                MainMenu.finishCSS=true;
            } else {
                MainMenu.blockViev();
                MainMenu.getTextArea().setText("Nie ma obecnie zadnych napraw.");
                MainMenu.jTextField.setEnabled(false);
                MainMenu.buttonOK.setEnabled(false);
            }
            MainMenu.jTextField.setBounds(415,650,450,50);
            MainMenu.buttonOK.setText("OK");
            newOkButton.setBounds(690,650,0,0);
            MainMenu.buttonOK.setBounds(865,650,100,50);
            MainMenu.buttonOK.setFocusPainted(false);
        });

        buttonNO.addActionListener(e -> {
            ublockAllButons(true);
            Map<IndependentCarServiceSpot, Vehicle> lista = IndependentCarServiceSpot.getAllServicedVehiclesList();
            MainMenu.getTextArea().setText("Lista napraw samodzielnych:\n\n ");
            MainMenu.getTextArea().append(""+lista);
            if (lista.isEmpty()) {
                MainMenu.blockViev();
                MainMenu.getTextArea().setText("Nie ma obecnie zadnych napraw.");
                MainMenu.jTextField.setEnabled(false);
                MainMenu.buttonOK.setEnabled(false);
            }else {
                MainMenu.finishICSS=true;
            }
            MainMenu.jTextField.setBounds(415,650,450,50);
            MainMenu.buttonOK.setText("OK");
            newOkButton.setBounds(690,650,0,0);
            MainMenu.buttonOK.setBounds(865,650,100,50);
            MainMenu.buttonOK.setFocusPainted(false);
        });


    }
    public static void ublockAllButons(boolean b){
        for (JButton jx:
                MainMenu.getjButtonList()) {
            jx.setEnabled(b);
        }
    }

}
