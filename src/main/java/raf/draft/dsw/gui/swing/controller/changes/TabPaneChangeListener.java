package raf.draft.dsw.gui.swing.controller.changes;
import raf.draft.dsw.gui.swing.model.tabs.TabPaneModel;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.gui.swing.view.Tab;
import raf.draft.dsw.gui.swing.view.TabPane;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class TabPaneChangeListener implements ChangeListener {
    TabPaneModel tabPaneModel;
    public TabPaneChangeListener(TabPaneModel tabPaneModel) {
        this.tabPaneModel = tabPaneModel;
    }
    @Override
    public void stateChanged(ChangeEvent e) {
        int selectedIndex = MainFrame.getInstance().getTabPane().getSelectedIndex();
        if(selectedIndex == -1) return;
        Tab tab = MainFrame.getInstance().getTabPaneModel().getOpenedTabs().get(selectedIndex);
        tabPaneModel.setActiveTab(tab);
    }
}
