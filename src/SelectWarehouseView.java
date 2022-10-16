import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Collectors;

public class SelectWarehouseView {
    private static boolean choosen=false;

    public SelectWarehouseView() {
        MainMenu.unlockViev();
        JButton newButtonOk = MainMenu.buttonOK;
        MainMenu.getTextArea().setText("Wybierz przedmiot ktory chcesz wlozyc:" + "\n\n");
        String listToPrint = Objects.getObjectList().stream()
                .map(String::valueOf)
                .collect(Collectors.joining("\n"));
        MainMenu.getTextArea().append(listToPrint);
        choosen=true;

        newButtonOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(choosen){
                    try {
                        Main.chooseItem(MainMenu.jTextField.getText());
                    } catch (TooManyThingsException ex) {
                        ex.printStackTrace();
                    }
                    MainMenu.jTextField.setEnabled(false);
                    MainMenu.buttonOK.setEnabled(false);
                    MainMenu.jTextField.setText("");
                    choosen=false;
                }
            }
        });
    }
}
