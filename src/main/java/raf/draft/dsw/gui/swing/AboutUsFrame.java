package raf.draft.dsw.gui.swing;

import javax.swing.*;
import java.awt.*;

public class AboutUsFrame extends JFrame {
    private static AboutUsFrame instance;
    private AboutUsFrame() {
        initialize();
    }
    public static AboutUsFrame getInstance() {
        if (instance == null) {
            instance = new AboutUsFrame();
        }
        return instance;
    }
    private void initialize() {
        setSize(550, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("About Us");

        JTextPane student1 = new JTextPane();
        student1.setText("Nikola Nedeljkovic SI 69/2024");
        student1.setEditable(false);
        student1.setBackground(getBackground());
        student1.setForeground(getForeground());
        student1.setMargin(new Insets(10, 10, 10, 10));

        JTextPane student2 = new JTextPane();
        student2.setText("Aleksandar Jovanovic SI 82/2024");
        student2.setBackground(getBackground());
        student2.setForeground(getForeground());
        student2.setMargin(new Insets(10, 10, 10, 10));

        JPanel j1 = new JPanel();
        j1.setLayout(new BorderLayout());
        j1.add(student1, BorderLayout.WEST);
        j1.add(student2, BorderLayout.EAST);
        add(j1, BorderLayout.CENTER);
    }
}
