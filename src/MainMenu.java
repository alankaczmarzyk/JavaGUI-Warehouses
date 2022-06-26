import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainMenu extends JFrame {
    public MainMenu() {
        JFrame frame = new JFrame("Warehouse.app");
        frame.setContentPane(new JLabel(new ImageIcon("images\\photo1.jpg")));
        ImageIcon img = new ImageIcon("images\\photo2.png");
        frame.setIconImage(img.getImage());
        frame.setLayout(null);
        frame.setMinimumSize(new Dimension(1380, 890));

        JButton jb1 = new JButton("Wybierz osobe");
        jb1.setFocusPainted(false);
        JButton jb2 = new JButton("Wyświetl informacje");
        jb2.setFocusPainted(false);
        JButton jb3 = new JButton("Wyświetl wolne pomieszczenia");
        jb2.setFocusPainted(false);
        JButton jb4 = new JButton("Wynajmij pomieszczenie");
        jb2.setFocusPainted(false);
        JButton jb5 = new JButton("Sprawdz zawartosc pomieszczenia");
        jb2.setFocusPainted(false);
        JButton jb6 = new JButton("Dodaj uprawnienia do magazynu");
        jb2.setFocusPainted(false);
        JButton jb7 = new JButton("Wloz przedmiot do magazynu");
        jb2.setFocusPainted(false);
        JButton jb8 = new JButton("Zaparkuj pojazd na miejscu parkingowym");
        jb2.setFocusPainted(false);
        JButton jb9 = new JButton("Zabierz przedmiot z magazynu");
        jb2.setFocusPainted(false);
        JButton jb10 = new JButton("Zabierz auto z parkingu");
        jb2.setFocusPainted(false);
        JButton jb11 = new JButton("Wynajmij miejsce serwisowe lub naprawcze");
        jb2.setFocusPainted(false);
        JButton jb12 = new JButton("Zglos potrzebe naprawy lub rozpocznij serwisowanie pojazdu");
        jb2.setFocusPainted(false);
        JButton jb13 = new JButton("Zakoncz naprawe pojazdu");
        jb2.setFocusPainted(false);
        JButton jb14 = new JButton("Zapisz aktualny stan");
        jb2.setFocusPainted(false);
        JButton jb15 = new JButton("Uruchom upływ czasu");
        jb2.setFocusPainted(false);
        JButton jb16 = new JButton("Wyjście z programu");
        jb2.setFocusPainted(false);

        jb1.setVisible(true);
        jb2.setVisible(true);
        jb3.setVisible(true);
        jb4.setVisible(true);
        jb5.setVisible(true);
        jb6.setVisible(true);
        jb7.setVisible(true);
        jb8.setVisible(true);
        jb9.setVisible(true);
        jb10.setVisible(true);
        jb11.setVisible(true);
        jb12.setVisible(true);
        jb13.setVisible(true);
        jb14.setVisible(true);
        jb15.setVisible(true);
        jb16.setVisible(true);

        jb1.setOpaque(true);
        jb2.setOpaque(true);
        jb3.setOpaque(true);
        jb4.setOpaque(true);
        jb5.setOpaque(true);
        jb6.setOpaque(true);
        jb7.setOpaque(true);
        jb8.setOpaque(true);
        jb9.setOpaque(true);
        jb10.setOpaque(true);
        jb11.setOpaque(true);
        jb12.setOpaque(true);
        jb13.setOpaque(true);
        jb14.setOpaque(true);
        jb15.setOpaque(true);
        jb16.setOpaque(true);

        frame.add(jb1);
        frame.add(jb2);
        frame.add(jb3);
        frame.add(jb4);
        frame.add(jb5);
        frame.add(jb6);
        frame.add(jb7);
        frame.add(jb8);
        frame.add(jb9);
        frame.add(jb10);
        frame.add(jb11);
        frame.add(jb12);
        frame.add(jb13);
        frame.add(jb14);
        frame.add(jb15);
        frame.add(jb16);

        jb1.setBounds(200,100,500,60);
        jb2.setBounds(200,180,500,60);
        jb3.setBounds(200,260,500,60);
        jb4.setBounds(200,340,500,60);
        jb5.setBounds(200,420,500,60);
        jb6.setBounds(200,500,500,60);
        jb7.setBounds(200,580,500,60);
        jb8.setBounds(200,660,500,60);

        jb9.setBounds(740,100,500,60);
        jb10.setBounds(740,180,500,60);
        jb11.setBounds(740,260,500,60);
        jb12.setBounds(740,340,500,60);
        jb13.setBounds(740,420,500,60);
        jb14.setBounds(740,500,500,60);
        jb15.setBounds(740,580,500,60);
        jb16.setBounds(740,660,500,60);

        jb16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
    }
}
