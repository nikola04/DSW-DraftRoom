package raf.draft.dsw.gui.swing.controller.actions;
import raf.draft.dsw.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class RotateElementLeftAction extends AbstractRoomAction {
    public RotateElementLeftAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        putValue(NAME, "Rotate Left");
        putValue(SMALL_ICON, loadIcon("/images/location.png"));
        putValue(SHORT_DESCRIPTION, "Rotate Element Left");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        MainFrame.getInstance().getProjectView().rotateLeft();
    }
}
