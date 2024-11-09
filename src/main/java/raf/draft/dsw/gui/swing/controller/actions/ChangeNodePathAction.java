package raf.draft.dsw.gui.swing.controller.actions;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.model.messages.MessageType;
import raf.draft.dsw.gui.swing.model.structures.Project;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class ChangeNodePathAction extends AbstractRoomAction {
    public ChangeNodePathAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        putValue(NAME, "Change Path");
        putValue(SMALL_ICON, loadIcon("/images/location.png"));
        putValue(SHORT_DESCRIPTION, "Change node path");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DraftTreeItem selected = MainFrame.getInstance().getDraftTree().getSelectedNode();
        if(selected.getDraftNode() instanceof Project project){
            String lastValue = project.getPath();
            Object nodePathObj = JOptionPane.showInputDialog(null, "Enter new path:", "Enter Path", JOptionPane.PLAIN_MESSAGE, null, null, lastValue);
            if(nodePathObj == null)
                return; // canceled
            String newPath = nodePathObj.toString();
            project.setPath(newPath);
            return;
        }
        ApplicationFramework.getInstance().getMessageGenerator().generateMessage("You can only change path on Projects", MessageType.WARNING);
    }
}
