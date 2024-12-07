package raf.draft.dsw.gui.swing.controller.actions;
import raf.draft.dsw.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MoveElementsAction extends AbstractRoomAction {
    public MoveElementsAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        putValue(NAME, "Move Elements");
        putValue(SMALL_ICON, loadIcon("/images/move.png"));
        putValue(SHORT_DESCRIPTION, "Move selected elements");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getProjectView().startMoveState();
    }
}
