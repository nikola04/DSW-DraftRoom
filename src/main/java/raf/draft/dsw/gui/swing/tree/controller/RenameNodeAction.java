package raf.draft.dsw.gui.swing.tree.controller;

import raf.draft.dsw.gui.swing.controller.actions.AbstractRoomAction;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RenameNodeAction extends AbstractRoomAction {
    public RenameNodeAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        putValue(NAME, "Rename Node");
        putValue(SMALL_ICON, loadIcon("/images/edit.png"));
        putValue(SHORT_DESCRIPTION, "Rename new node");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DraftTreeItem selected = MainFrame.getInstance().getDraftTree().getSelectedNode();
        MainFrame.getInstance().getDraftTree().renameNode(selected);
    }
}
