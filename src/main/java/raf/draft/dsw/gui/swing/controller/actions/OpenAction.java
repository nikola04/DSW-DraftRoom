package raf.draft.dsw.gui.swing.controller.actions;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.model.structures.Project;
import raf.draft.dsw.gui.swing.model.structures.ProjectExplorer;
import raf.draft.dsw.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class OpenAction extends AbstractRoomAction {
    public OpenAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("/images/open.png"));
        putValue(NAME, "Open");
        putValue(SHORT_DESCRIPTION, "Open Project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc = new JFileChooser();

        if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            File file = jfc.getSelectedFile();
            Project loadedProject = ApplicationFramework.getInstance().getSerializer().loadProject(file);
            if(loadedProject == null) return;
            MainFrame.getInstance().getDraftTree().loadProject(loadedProject);
            ProjectExplorer explorer = ApplicationFramework.getInstance().getDraftRoomRepository().getProjectExplorer();
            loadedProject.setParent(explorer);
            explorer.addChild(loadedProject);
        }
    }
}
