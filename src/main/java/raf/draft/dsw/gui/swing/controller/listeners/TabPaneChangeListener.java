package raf.draft.dsw.gui.swing.controller.listeners;
import raf.draft.dsw.gui.swing.model.tabs.TabPaneModel;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.gui.swing.view.RoomView;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TabPaneChangeListener implements ChangeListener {
    TabPaneModel tabPaneModel;
    public TabPaneChangeListener(TabPaneModel tabPaneModel) {
        this.tabPaneModel = tabPaneModel;
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        int selectedIndex = MainFrame.getInstance().getTabPane().getSelectedIndex();
        if(selectedIndex == -1) return;
        RoomView tab = MainFrame.getInstance().getTabPaneModel().getOpenedTabs().get(selectedIndex);
        tabPaneModel.setActiveTab(tab);
    }
}
