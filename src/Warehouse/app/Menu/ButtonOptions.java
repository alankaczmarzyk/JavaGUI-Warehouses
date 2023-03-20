package Warehouse.app.Menu;

import javax.swing.*;
import java.awt.*;

public class ButtonOptions extends JFrame{
    public ButtonOptions(JButton button, Frame frame) {
        button.setVisible(true);
        button.setOpaque(true);
        button.setFocusPainted(false);
        frame.add(button);
    }

    public void setSize(JButton button, int x,int y, int width, int heigth){
        button.setBounds(x,y,width,heigth);
    }
}
