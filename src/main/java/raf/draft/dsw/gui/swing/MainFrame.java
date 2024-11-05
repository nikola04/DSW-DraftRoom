package raf.draft.dsw.gui.swing;

import raf.draft.dsw.controller.observer.ISubscriber;
import raf.draft.dsw.model.messages.Message;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements ISubscriber {
    private static MainFrame instance;
    private MainFrame() {
        initialize();
    }
    public static MainFrame getInstance() {
        if (instance == null)
            instance = new MainFrame();
        return instance;
    }
    private void initialize(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("DraftRoom");

        MyMenuBar menu = new MyMenuBar();
        setJMenuBar(menu);

        MyToolBar toolBar = new MyToolBar();
        add(toolBar, BorderLayout.NORTH);
    }

    @Override
    public void update(Message message) {
        String messageType = message.getType().toString();
        String messageText = message.toString();

        int messageTypeJOptionPane;
        switch (message.getType()) {
            case ERROR:
                messageTypeJOptionPane = JOptionPane.ERROR_MESSAGE;
                break;
            case WARNING:
                messageTypeJOptionPane = JOptionPane.WARNING_MESSAGE;
                break;
            case INFO:
                messageTypeJOptionPane = JOptionPane.INFORMATION_MESSAGE;
                break;
            default:
                messageTypeJOptionPane = JOptionPane.PLAIN_MESSAGE;
        }

        JOptionPane.showMessageDialog(this, messageText, messageType, messageTypeJOptionPane);
    }
}
