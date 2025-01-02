package raf.draft.dsw.gui.swing.controller.actions.room;
import raf.draft.dsw.gui.swing.controller.actions.AbstractRoomAction;
import raf.draft.dsw.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class RotateElementRightAction extends AbstractRoomAction {
    public RotateElementRightAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        putValue(NAME, "Rotate Right");
        putValue(SMALL_ICON, loadIcon("/images/rotate-right.png"));
        putValue(SHORT_DESCRIPTION, "Rotate Element Right");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().rotateSelectedElements(1);
    }
}
