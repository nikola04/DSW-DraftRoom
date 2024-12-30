package raf.draft.dsw.gui.swing.controller.actions;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.model.messages.MessageType;
import raf.draft.dsw.gui.swing.model.structures.Project;
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
        if (MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode() instanceof Project project) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Choose project directory");
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

            int userSelection = fileChooser.showSaveDialog(null);
            if (userSelection != JFileChooser.APPROVE_OPTION) return;

            try {
                File dir = fileChooser.getSelectedFile();
                if(!dir.isDirectory()) dir = dir.getParentFile();
                String path = new File(dir, project.getName() + ".json").getCanonicalPath();
                project.setPath(path);
                ApplicationFramework.getInstance().getSerializer().saveProject(project);
                project.setChanged(false);
            } catch (IOException ex) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Error choosing project directory.", MessageType.ERROR);
            }

        }
    }
}
