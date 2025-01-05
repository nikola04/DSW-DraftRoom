package raf.draft.dsw.gui.swing.controller.actions;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.model.messages.MessageType;
import raf.draft.dsw.gui.swing.model.structures.Room;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URL;

public class SaveTemplateAction extends AbstractRoomAction {
    public SaveTemplateAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK | InputEvent.ALT_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("/images/save.png"));
        putValue(NAME, "Save Template");
        putValue(SHORT_DESCRIPTION, "Save room as template");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Room room = getCurrentRoom();
        if (room == null) return;

        File dir = getResourceDir();
        if(dir == null) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Failed to get templates directory.", MessageType.ERROR);
            return;
        }

        String fileName = JOptionPane.showInputDialog("Enter template name:");
        if (fileName == null || fileName.isEmpty()) return; // user canceled

        File templateFile = new File(dir, fileName + ".json");
        ApplicationFramework.getInstance().getSerializer().saveTemplate(room, templateFile.getPath());
    }

    private Room getCurrentRoom() {
        DraftTreeItem selected = MainFrame.getInstance().getDraftTree().getSelectedNode();
        if(selected != null && selected.getDraftNode() instanceof Room room) return room; // try to find selected room
        return MainFrame.getInstance().getProjectView().getCurrentRoom(); // try to find opened room
    }

    private File getResourceDir(){
        URL templatesURL = getClass().getClassLoader().getResource("templates");
        if(templatesURL != null) {
            File dir = new File(templatesURL.getFile());
            if(dir.exists()) return dir;
        }

        URL resourceRoot = getClass().getClassLoader().getResource("");
        if (resourceRoot != null) {
            File resourceDir = new File(resourceRoot.getFile());
            File templatesDir = new File(resourceDir, "templates");
            if (!templatesDir.exists())
                if (!templatesDir.mkdirs()) return null;
            return templatesDir;
        }
        return null;
    }
}
