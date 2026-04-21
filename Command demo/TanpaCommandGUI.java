import javax.swing.*;
import java.awt.event.*;

public class TanpaCommandGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tanpa Command");
        JButton btnOn = new JButton("ON");
        JButton btnOff = new JButton("OFF");

        frame.setLayout(new java.awt.FlowLayout());

        // Logic langsung di button
        btnOn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Lampu dinyalakan");
            }
        });

        btnOff.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Lampu dimatikan");
            }
        });

        frame.add(btnOn);
        frame.add(btnOff);

        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}