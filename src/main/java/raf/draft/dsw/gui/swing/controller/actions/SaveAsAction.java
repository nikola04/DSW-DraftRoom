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

public class SaveAsAction extends AbstractRoomAction {
    public SaveAsAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("/images/save.png"));
        putValue(NAME, "Save As");
        putValue(SHORT_DESCRIPTION, "Save As");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DraftTreeItem selected = MainFrame.getInstance().getDraftTree().getSelectedNode();
        if(selected == null) return;
        Project project = selected.getDraftNode().findParentProject();
        if (project != null) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save project as...");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setSelectedFile(new File(project.getName() + ".json"));

            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection != JFileChooser.APPROVE_OPTION) return;

            try {
                File file = fileChooser.getSelectedFile();
                if (!file.getName().toLowerCase().endsWith(".json"))
                    file = new File(file.getParentFile(), file.getName() + ".json");

                String path = file.getCanonicalPath();
                project.setPath(path);
                ApplicationFramework.getInstance().getSerializer().saveProject(project);
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Project is saved successfully.", MessageType.INFO);
                project.setChanged(false);
            } catch (IOException ex) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Error saving project file.", MessageType.ERROR);
            }
        }

    }
}
