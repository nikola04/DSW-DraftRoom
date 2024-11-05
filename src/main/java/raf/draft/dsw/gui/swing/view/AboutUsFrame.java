package raf.draft.dsw.gui.swing.view;

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
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setTitle("About Us");

        // Student 1
        JTextPane student1 = new JTextPane();
        student1.setText("Nikola Nedeljkovic SI 69/2024");
        student1.setEditable(false);
        student1.setBackground(getBackground());
        student1.setForeground(getForeground());
        student1.setMargin(new Insets(10, 10, 10, 10));

        Image img1 = new ImageIcon(getClass().getResource("/images/nikola_image.jpg")).getImage();
        Image newImg1 = img1.getScaledInstance(200, 270, Image.SCALE_SMOOTH);
        Icon icon1 = new ImageIcon(newImg1);

        JLabel imageLabel1 = new JLabel(icon1);

        JTextPane student2 = new JTextPane();
        student2.setText("Aleksandar Jovanovic SI 82/2024");
        student2.setEditable(false);
        student2.setBackground(getBackground());
        student2.setForeground(getForeground());
        student2.setMargin(new Insets(10, 10, 10, 10));

        Image img2 = new ImageIcon(getClass().getResource("/images/aleksandar_image.jpeg")).getImage();
        Image newImg2 = img2.getScaledInstance(200, 270, Image.SCALE_DEFAULT);
        Icon icon2 = new ImageIcon(newImg2);

        JLabel imageLabel2 = new JLabel(icon2);

        JPanel student1Panel = new JPanel(new BorderLayout());
        student1Panel.add(student1, BorderLayout.NORTH);
        student1Panel.add(imageLabel1, BorderLayout.CENTER);

        JPanel student2Panel = new JPanel(new BorderLayout());
        student2Panel.add(student2, BorderLayout.NORTH);
        student2Panel.add(imageLabel2, BorderLayout.CENTER);

        JPanel mainPanel = new JPanel(new GridLayout(1, 2));
        mainPanel.add(student1Panel);
        mainPanel.add(student2Panel);
        add(mainPanel, BorderLayout.CENTER);
    }
}
