package raf.draft.dsw.gui.swing.controller.actions.room;
import raf.draft.dsw.gui.swing.controller.actions.AbstractRoomAction;
import raf.draft.dsw.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class AddElementAction extends AbstractRoomAction {
    public AddElementAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        putValue(NAME, "Add Element");
        putValue(SMALL_ICON, loadIcon("/images/furniture.png"));
        putValue(SHORT_DESCRIPTION, "Add new element to room");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startAddState();
    }
}
