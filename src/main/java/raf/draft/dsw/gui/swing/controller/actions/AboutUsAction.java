package raf.draft.dsw.gui.swing.controller.actions;

import raf.draft.dsw.gui.swing.view.AboutUsFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AboutUsAction extends AbstractRoomAction {
    public AboutUsAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/info.png"));
        putValue(NAME, "About");
        putValue(SHORT_DESCRIPTION, "About Us");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AboutUsFrame auFrame = AboutUsFrame.getInstance();
        auFrame.setVisible(true);
    }

    @Override
    public boolean accept(Object sender) {
        return super.accept(sender);
    }
}
