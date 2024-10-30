package raf.draft.dsw.gui.swing;

import raf.draft.dsw.controller.actions.AboutUsAction;
import raf.draft.dsw.controller.actions.ExitAction;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {
    public MyMenuBar(){
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        ExitAction ea = new ExitAction();
        AboutUsAction ab = new AboutUsAction();
        fileMenu.add(ea);
        fileMenu.add(ab);
        add(fileMenu);
    }
}
