package raf.draft.dsw.gui.swing.controller.actions;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.model.structures.Project;
import raf.draft.dsw.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class SaveAction extends AbstractRoomAction {
    public SaveAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("/images/save.png"));
        putValue(NAME, "Save");
        putValue(SHORT_DESCRIPTION, "Save");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (MainFrame.getInstance().getDraftTree().getSelectedNode().getDraftNode() instanceof Project project) {
            if (!project.isChanged()) return;
            String path = project.getPath();
            if(path == null) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Choose project directory");
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                int userSelection = fileChooser.showSaveDialog(null);
                if (userSelection != JFileChooser.APPROVE_OPTION) return;

                File dir = fileChooser.getSelectedFile();
                path = new File(dir.getAbsolutePath(), project.getName() + ".json").getPath();
            }
            project.setPath(path);
            ApplicationFramework.getInstance().getSerializer().saveProject(project);
            project.setChanged(false);
        }
    }
}
