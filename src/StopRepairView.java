import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Collectors;

public class StopRepairView {
    public StopRepairView(){
        MainMenu.getTextArea().setText("Jaki rodzaj naprawy chcesz zakonczyc?");
        MainMenu.jTextField.setBounds(415,650,0,0);
        MainMenu.buttonOK.setBounds(415,650,0,0);

        JButton newOkButton = new JButton("Samodzielna naprawa");
        newOkButton.setBounds(415,650,275,50);
        newOkButton.setFocusPainted(false);
        MainMenu.frame.add(newOkButton);


        JButton buttonNO = new JButton("Serwisowa naprawa");
        buttonNO.setBounds(690,650,275,50);
        buttonNO.setFocusPainted(false);
        MainMenu.frame.add(buttonNO);
        buttonNO.setOpaque(true);
        buttonNO.setOpaque(true);

        newOkButton.addActionListener(e -> {
            MainMenu.jTextField.setBounds(415,650,450,50);
            MainMenu.buttonOK.setText("OK");
            newOkButton.setBounds(690,650,0,0);
            MainMenu.buttonOK.setBounds(865,650,100,50);
            MainMenu.buttonOK.setFocusPainted(false);
            MainMenu.finishICSS=true;
        });

        buttonNO.addActionListener(e -> {
            newOkButton.setBounds(690,650,0,0);
            buttonNO.setBounds(690,650,0,0);
            MainMenu.buttonOK.setBounds(865,650,100,50);
            MainMenu.buttonOK.setFocusPainted(false);
            MainMenu.jTextField.setBounds(415,650,450,50);
            MainMenu.finishCSS=true;

        });


    }

}
