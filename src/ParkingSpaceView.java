import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Collectors;

public class ParkingSpaceView {
    public ParkingSpaceView(){
        MainMenu.getTextArea().setText("Czy chcesz wynajac miejsce parkingowe?");
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
            MainMenu.getTextArea().setText("Wybierz miejsce parkingowe ktore chcesz wynajac:\n\n");
            Main.updateFreeParkingList();

            String listToPrint = Main.freeParkingSpaceList.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining("\n"));

            MainMenu.getTextArea().append(""+listToPrint);
            MainMenu.jTextField.setBounds(415,650,450,50);
            MainMenu.buttonOK.setText("OK");
            newOkButton.setBounds(690,650,0,0);
            MainMenu.buttonOK.setBounds(865,650,100,50);
            MainMenu.buttonOK.setFocusPainted(false);
            MainMenu.chooseParking=true;
            Main.needParking=true;
        });

        buttonNO.addActionListener(e -> {
            Main.needParking=false;
            Main.rentParking("");
            newOkButton.setBounds(690,650,0,0);
            buttonNO.setBounds(690,650,0,0);
            MainMenu.buttonOK.setBounds(865,650,100,50);
            MainMenu.buttonOK.setFocusPainted(false);
            MainMenu.jTextField.setBounds(415,650,450,50);
            MainMenu.jTextField.setEnabled(false);
        });


    }

}
