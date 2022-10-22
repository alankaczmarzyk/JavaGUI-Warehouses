import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Collectors;

public class ServiceRepairView {
    public ServiceRepairView(){
        MainMenu.getTextArea().setText("Czy chcesz samodzielnie serwisowac pojazd?");
        ublockAllButons(false);
        MainMenu.jTextField.setBounds(415,650,0,0);
        MainMenu.buttonOK.setBounds(415,650,0,0);

        JButton newOkButton = new JButton("Tak");
        newOkButton.setBounds(415,650,275,50);
        newOkButton.setFocusPainted(false);
        MainMenu.frame.add(newOkButton);


        JButton buttonNO = new JButton("Nie");
        buttonNO.setBounds(690,650,275,50);
        buttonNO.setFocusPainted(false);
        MainMenu.frame.add(buttonNO);
        buttonNO.setOpaque(true);
        buttonNO.setOpaque(true);

        newOkButton.addActionListener(e -> {
            MainMenu.unlockViev();
            ublockAllButons(true);
            MainMenu.getTextArea().setText("Wybrano samodzielna naprawe.\n\n");
            MainMenu.getTextArea().append("Wybierz miejsce serwisowe:\n\n");

            String listToPrint = Main.serviceSpots.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining("\n"));
            MainMenu.getTextArea().append(""+listToPrint);
            MainMenu.jTextField.setBounds(415,650,450,50);
            MainMenu.buttonOK.setText("OK");
            newOkButton.setBounds(690,650,0,0);
            MainMenu.buttonOK.setBounds(865,650,100,50);
            MainMenu.buttonOK.setFocusPainted(false);
            MainMenu.serviceSpotChosen=true;
        });

        buttonNO.addActionListener(e -> {
            MainMenu.unlockViev();
            ublockAllButons(true);
            MainMenu.getTextArea().setText("Wybrano naprawe przez mechaników.\n\n");
            MainMenu.getTextArea().append("Wybierz miejsce naprawcze:\n\n");

            String listToPrint = Main.repairPlaces.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining("\n"));
            MainMenu.getTextArea().append(""+listToPrint);

            newOkButton.setBounds(690,650,0,0);
            buttonNO.setBounds(690,650,0,0);
            MainMenu.buttonOK.setBounds(865,650,100,50);
            MainMenu.buttonOK.setFocusPainted(false);
            MainMenu.buttonOK.setText("OK");
            MainMenu.jTextField.setBounds(415,650,450,50);
            MainMenu.jTextField.setEnabled(true);
            MainMenu.repairPlace=true;
        });


    }
    public static void ublockAllButons(boolean b){
        for (JButton jx:
                MainMenu.getjButtonList()) {
            jx.setEnabled(b);
        }
    }

}
