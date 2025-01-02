package raf.draft.dsw.gui.swing.controller.actions;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.model.messages.MessageType;
import raf.draft.dsw.gui.swing.model.structures.Project;
import raf.draft.dsw.gui.swing.model.structures.Room;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;

public class SavePatternAction extends AbstractRoomAction {
    public SavePatternAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK | InputEvent.ALT_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("/images/save.png"));
        putValue(NAME, "Save Pattern");
        putValue(SHORT_DESCRIPTION, "Save Room Pattern");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DraftTreeItem selected = MainFrame.getInstance().getDraftTree().getSelectedNode();
        if(selected == null) return;
        if (selected.getDraftNode() instanceof Room room) {
            String resourcePath = "src/resources/templates"; // Putanja za razvojno okruženje
            File dir = new File(resourcePath);
            if (!dir.exists()) {
                boolean created = dir.mkdirs();
            }
//            URL resourceUrl = getClass().getResource("/templates/");
//            if (resourceUrl == null) {
//                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Template directory not found!", MessageType.ERROR);
//                return;
//            }
//            File dir = new File(resourceUrl.getFile());
//            if (!dir.exists()) {
//                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Template directory is missing or inaccessible!", MessageType.ERROR);
//                return;
//            }
            String fileName = JOptionPane.showInputDialog("Enter template name:");
            if (fileName == null || fileName.isEmpty()) return;

            File templateFile = new File(dir, fileName + ".json");
            ApplicationFramework.getInstance().getSerializer().savePattern(room, templateFile.getPath());
        }
    }
}
