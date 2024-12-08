package raf.draft.dsw.gui.swing.controller.actions;
import raf.draft.dsw.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class EditElementAction extends AbstractRoomAction {
    public EditElementAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        putValue(NAME, "Edit Element");
        putValue(SMALL_ICON, loadIcon("/images/edit-icon.png"));
        putValue(SHORT_DESCRIPTION, "Edit selected element");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startEditState();
    }
}
