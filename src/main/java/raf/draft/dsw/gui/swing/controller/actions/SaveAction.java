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
import java.io.File;
import java.io.IOException;

public class SaveAction extends AbstractRoomAction {
    public SaveAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("/images/save.png"));
        putValue(NAME, "Save");
        putValue(SHORT_DESCRIPTION, "Save");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DraftTreeItem selected = MainFrame.getInstance().getDraftTree().getSelectedNode();
        if(selected == null) return;
        if (selected.getDraftNode().findParentProject() != null) {
            Project project = selected.getDraftNode().findParentProject();
            if (!project.isChanged()) return;
            String path = project.getPath();
            if(path == null) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choose project directory");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int userSelection = fileChooser.showSaveDialog(null);
                if (userSelection != JFileChooser.APPROVE_OPTION) return;

                try {
                    File dir = fileChooser.getSelectedFile();
                    if(!dir.isDirectory()) dir = dir.getParentFile();
                    path = new File(dir, project.getName() + ".json").getCanonicalPath();
                    project.setPath(path);
                } catch (IOException ex) {
                    ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Error choosing project directory.", MessageType.ERROR);
                    return;
                }
            }
            ApplicationFramework.getInstance().getSerializer().saveProject(project);
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Project is saved successfully.", MessageType.INFO);
            project.setChanged(false);
        }
    }
}
