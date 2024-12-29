package raf.draft.dsw.gui.swing.controller.actions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class SaveAsAction extends AbstractRoomAction {
    public SaveAsAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("/images/save.png"));
        putValue(NAME, "Save As");
        putValue(SHORT_DESCRIPTION, "Save As");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
