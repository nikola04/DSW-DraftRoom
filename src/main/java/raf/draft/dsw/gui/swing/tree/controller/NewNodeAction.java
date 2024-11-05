package raf.draft.dsw.gui.swing.tree.controller;

import raf.draft.dsw.gui.swing.controller.actions.AbstractRoomAction;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.model.structures.ProjectExplorer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewNodeAction extends AbstractRoomAction {
    public NewNodeAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        putValue(NAME, "New Node");
        putValue(SMALL_ICON, loadIcon("/images/plus.png"));
        putValue(SHORT_DESCRIPTION, "Add new node");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DraftTreeItem selected = MainFrame.getInstance().getDraftTree().getSelectedNode();
        if(selected == null)
            return;

        String nodeName = "";
        if(selected.getDraftNode() instanceof ProjectExplorer)
            nodeName = JOptionPane.showInputDialog("Enter project name:");

        MainFrame.getInstance().getDraftTree().addChild(selected, nodeName);
    }
}
