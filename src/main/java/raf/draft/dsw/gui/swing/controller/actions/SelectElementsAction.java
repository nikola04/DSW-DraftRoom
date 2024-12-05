package raf.draft.dsw.gui.swing.controller.actions;
import raf.draft.dsw.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class SelectElementsAction extends AbstractRoomAction {
    public SelectElementsAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        putValue(NAME, "Select Elements");
        putValue(SMALL_ICON, loadIcon("/images/selection.png"));
        putValue(SHORT_DESCRIPTION, "Select elements");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startSelectionState();
    }
}
