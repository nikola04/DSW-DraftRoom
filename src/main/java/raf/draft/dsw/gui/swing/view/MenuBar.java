package raf.draft.dsw.gui.swing.view;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar {
    public MenuBar() {
        setBorderPainted(false);
        // File Menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(MainFrame.getInstance().getActionManager().getAboutUsAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getExitAction());

        // Edit Menu
        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        editMenu.add(MainFrame.getInstance().getActionManager().getNewNodeAction());
        editMenu.add(MainFrame.getInstance().getActionManager().getRenameNodeAction());
        editMenu.add(MainFrame.getInstance().getActionManager().getChangeNodePathAction());
        editMenu.add(MainFrame.getInstance().getActionManager().getChangeNodeAuthorAction());
        editMenu.add(MainFrame.getInstance().getActionManager().getDeleteNodeAction());
        editMenu.add(MainFrame.getInstance().getActionManager().getUndoAction());
        editMenu.add(MainFrame.getInstance().getActionManager().getRedoAction());

        add(fileMenu);
        add(editMenu);
    }
}
