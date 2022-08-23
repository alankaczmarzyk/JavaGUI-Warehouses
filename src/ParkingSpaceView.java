import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParkingSpaceView {
    public ParkingSpaceView(){
        MainMenu.getTextArea().setText("Czy chcesz wynajac miejsce parkingowe?");
        MainMenu.jTextField.setBounds(415,650,0,0);
        MainMenu.buttonOK.setBounds(415,650,275,50);
        MainMenu.buttonOK.setText("Tak");

        JButton buttonNO = new JButton("Nie");
        buttonNO.setBounds(690,650,275,50);
        buttonNO.setFocusPainted(false);
        MainMenu.frame.add(buttonNO);
        buttonNO.setOpaque(true);
        buttonNO.setOpaque(true);

        MainMenu.buttonOK.addActionListener(e -> {
            MainMenu.getTextArea().setText("Wybierz miejsce parkingowe ktore chcesz wynajac:\n\n");
            Main.updateFreeParkingList();
            MainMenu.getTextArea().append(""+Main.freeParkingSpaceList);
            MainMenu.jTextField.setBounds(415,650,450,50);
            MainMenu.buttonOK.setText("OK");
            buttonNO.setBounds(690,650,0,0);
            MainMenu.buttonOK.setBounds(865,650,100,50);
            MainMenu.buttonOK.setFocusPainted(false);
        });

        buttonNO.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Main.needParking = false;
            }
        });


    }

}
