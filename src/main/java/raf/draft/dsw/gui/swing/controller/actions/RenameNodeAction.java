package raf.draft.dsw.gui.swing.controller.actions;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.controller.commands.concrete.RenameCommand;
import raf.draft.dsw.gui.swing.model.messages.MessageType;
import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class RenameNodeAction extends AbstractRoomAction {
    public RenameNodeAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
        putValue(NAME, "Rename Node");
        putValue(SMALL_ICON, loadIcon("/images/edit.png"));
        putValue(SHORT_DESCRIPTION, "Rename node");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DraftTreeItem selected = MainFrame.getInstance().getDraftTree().getSelectedNode();
        if(selected == null || selected.getDraftNode() == null) return;
        DraftNode node = selected.getDraftNode();
        String originalName = node.getName();
        Object nodeNameObj = JOptionPane.showInputDialog(null, "Enter new name:", "Enter Name", JOptionPane.PLAIN_MESSAGE, null, null, originalName);
        if(nodeNameObj == null) return;
        String nodeName = nodeNameObj.toString();
        if(nodeName.isEmpty()){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Node Name cannot be empty", MessageType.ERROR);
            return;
        }
        RenameCommand command = new RenameCommand(node, originalName, nodeName);
        ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
        ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Name is changed successfully", MessageType.INFO);
    }
}
