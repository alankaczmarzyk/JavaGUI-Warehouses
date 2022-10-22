import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Collectors;

public class ParkingSpaceView {
    private static JButton cancelButton;

    public ParkingSpaceView(){
        ublockAllButons(false);
        MainMenu.getTextArea().setText("Czy chcesz wynajac miejsce parkingowe?");
        MainMenu.jTextField.setBounds(415,650,0,0);
        MainMenu.buttonOK.setBounds(415,650,0,0);
        MainMenu.jTextArea.setBounds(415,100,550,500);
        MainMenu.scroll.setBounds(415,100,550,500);

        cancelButton = new JButton("Anuluj");
        cancelButton.setBounds(415,600,550,50);
        cancelButton.setFocusPainted(false);
        MainMenu.frame.add(cancelButton);

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
            ublockAllButons(true);
            cancelButton.setBounds(415,600,0,0);
            MainMenu.jTextArea.setBounds(415,100,550,550);
            MainMenu.scroll.setBounds(415,100,550,550);
            MainMenu.getTextArea().setText("Wybierz miejsce parkingowe ktore chcesz wynajac:\n\n");
            Main.updateFreeParkingList();

            String listToPrint = Main.freeParkingSpaceList.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining("\n"));

            if(listToPrint.isEmpty()){
                MainMenu.blockViev();
                MainMenu.getTextArea().append("Wszystkie miejsca parkingowe są zajete.");
            }else MainMenu.getTextArea().append(""+listToPrint);

            MainMenu.jTextField.setBounds(415,650,450,50);
            MainMenu.buttonOK.setText("OK");
            newOkButton.setBounds(690,650,0,0);
            MainMenu.buttonOK.setBounds(865,650,100,50);
            MainMenu.buttonOK.setFocusPainted(false);
            MainMenu.chooseParking=true;
            Main.needParking=true;
        });

        buttonNO.addActionListener(e -> {
            cancelButton.setBounds(415,600,0,0);
            MainMenu.jTextArea.setBounds(415,100,550,550);
            MainMenu.scroll.setBounds(415,100,550,550);
            ublockAllButons(true);
            Main.needParking=false;
            Main.rentParking("");
            newOkButton.setBounds(690,650,0,0);
            buttonNO.setBounds(690,650,0,0);
            MainMenu.buttonOK.setBounds(865,650,100,50);
            MainMenu.buttonOK.setFocusPainted(false);
            MainMenu.jTextField.setBounds(415,650,450,50);
            MainMenu.jTextField.setEnabled(false);
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelButton.setBounds(415,600,0,0);
                ublockAllButons(true);
                MainMenu.frame.dispose();
                MainMenu m1 = new MainMenu<>();
            }
        });

    }
    public static void ublockAllButons(boolean b){
        for (JButton jx:
                MainMenu.getjButtonList()) {
            jx.setEnabled(b);
        }
    }
}
