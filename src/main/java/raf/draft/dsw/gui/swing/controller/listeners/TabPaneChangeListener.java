package raf.draft.dsw.gui.swing.controller.listeners;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.model.messages.MessageType;
import raf.draft.dsw.gui.swing.model.structures.Room;
import raf.draft.dsw.gui.swing.model.tabs.TabPaneModel;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.gui.swing.view.RoomView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.io.File;

public class TabPaneChangeListener implements ChangeListener {
    TabPaneModel tabPaneModel;
    public TabPaneChangeListener(TabPaneModel tabPaneModel) {
        this.tabPaneModel = tabPaneModel;
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        int selectedIndex = MainFrame.getInstance().getTabPane().getSelectedIndex();
        if(selectedIndex == -1) return;
        RoomView roomView = MainFrame.getInstance().getTabPaneModel().getOpenedTabs().get(selectedIndex);
        roomView.refresh();
        Room room = roomView.getRoom();
        tabPaneModel.setActiveTab(room);
        if(!room.isInitialized()) {
            room.initialize();
            int response = JOptionPane.showConfirmDialog(null, "Would you like to load the template?", "Load Template", JOptionPane.YES_NO_OPTION);
            if (response != JOptionPane.YES_OPTION) return;

            String path = "src/resources/templates";
            File dir = new File(path);
//            if (!dir.exists() || !dir.isDirectory())
//                dir = new File(getClass().getClassLoader().getResource("templates").getFile());
            JFileChooser fileChooser = new JFileChooser(dir);

            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int result = fileChooser.showOpenDialog(null);
            if (result != JFileChooser.APPROVE_OPTION) return;

            File selectedFile = fileChooser.getSelectedFile();
            boolean loaded = room.loadTemplate(selectedFile);
            if(loaded) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Successfully loaded template.", MessageType.INFO);
            }
        }
    }
}
