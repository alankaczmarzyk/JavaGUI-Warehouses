package Warehouse.app.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homepage extends JFrame {
    public Homepage(){
        JFrame frame = new JFrame("Warehouse.app");
        frame.setContentPane(new JLabel(new ImageIcon("images\\photo.jpg")));
        ImageIcon img = new ImageIcon("images\\photo2.png");
        frame.setIconImage(img.getImage());
        frame.setLayout(null);
        frame.setMinimumSize(new Dimension(1380, 890));
        JButton jb1 = new JButton("Rozpocznij");
        jb1.setFocusPainted(false);
        JButton jb2 = new JButton("Wyjście");
        jb2.setFocusPainted(false);

        jb1.setBounds(0,280,1380,60);
        jb2.setBounds(0,350,1380,60);

        jb1.setVisible(true);
        jb2.setVisible(true);

        jb1.setOpaque(true);
        jb2.setOpaque(true);

        jb1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                SwingUtilities.invokeLater(MainMenu::new);
            }
        });

        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.add(jb1);
        frame.add(jb2);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
}
