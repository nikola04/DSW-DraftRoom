package raf.draft.dsw.gui.swing.controller.actions;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.controller.commands.concrete.ChangeAuthorCommand;
import raf.draft.dsw.gui.swing.model.messages.MessageType;
import raf.draft.dsw.gui.swing.model.structures.Project;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class ChangeNodeAuthorAction extends AbstractRoomAction {
    public ChangeNodeAuthorAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
        putValue(NAME, "Change Author");
        putValue(SMALL_ICON, loadIcon("/images/lock.png"));
        putValue(SHORT_DESCRIPTION, "Change node author");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DraftTreeItem selected = MainFrame.getInstance().getDraftTree().getSelectedNode();
        if(selected.getDraftNode() instanceof Project project){
            String lastValue = project.getAuthor();
            Object nodeAuthorObj = JOptionPane.showInputDialog(null, "Enter new author:", "Enter Author", JOptionPane.PLAIN_MESSAGE, null, null, lastValue);
            if(nodeAuthorObj == null) return;
            String newAuthor = nodeAuthorObj.toString();
            ChangeAuthorCommand command = new ChangeAuthorCommand(project, lastValue, newAuthor);
            ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(command);
            return;
        }
        ApplicationFramework.getInstance().getMessageGenerator().generateMessage("You can only change author on Projects", MessageType.WARNING);
    }
}
