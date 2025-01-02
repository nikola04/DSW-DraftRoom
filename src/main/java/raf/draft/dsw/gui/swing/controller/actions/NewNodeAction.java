package raf.draft.dsw.gui.swing.controller.actions;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.controller.commands.concrete.AddNodeCommand;
import raf.draft.dsw.gui.swing.model.messages.MessageType;
import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.nodes.DraftNodeComposite;
import raf.draft.dsw.gui.swing.model.structures.Project;
import raf.draft.dsw.gui.swing.model.structures.Room;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class NewNodeAction extends AbstractRoomAction {
    public NewNodeAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        putValue(NAME, "New Node");
        putValue(SMALL_ICON, loadIcon("/images/plus.png"));
        putValue(SHORT_DESCRIPTION, "Add new node");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DraftTreeItem selected = MainFrame.getInstance().getDraftTree().getSelectedNode();
        if(selected == null) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Please select Explorer, Building or Room first", MessageType.WARNING);
            return;
        }
        if(!(selected.getDraftNode() instanceof DraftNodeComposite parent) || parent instanceof Room) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("You cannot add anything here. Please select Explorer, Project or Building", MessageType.WARNING);
            return;
        }
        DraftNode child = MainFrame.getInstance().getDraftTree().createNode(parent);
        if(child == null) return;
        AddNodeCommand addNodeCommand = new AddNodeCommand(parent, child);
        ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(addNodeCommand);
    }
}
