package raf.draft.dsw.gui.swing.tree.controller;

import raf.draft.dsw.gui.swing.tree.DraftTreeImplementation;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.model.nodes.DraftNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewProjectAction extends AbstractAction {
    public NewProjectAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        putValue(NAME, "New Project");
        putValue(SHORT_DESCRIPTION, "Add new project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nodeName = JOptionPane.showInputDialog("Enter project name:");
//        DraftTreeItem selected = MainFrame.getInstance().getDraftTree().getSelectedNode();
//        MainFrame.getInstance().getDraftTree().addChild(selected, newNode);
    }
}
