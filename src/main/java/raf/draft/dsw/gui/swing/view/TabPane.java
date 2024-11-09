package raf.draft.dsw.gui.swing.view;

import raf.draft.dsw.gui.swing.controller.changes.TabPaneChangeListener;
import raf.draft.dsw.gui.swing.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.model.events.EventModel;
import raf.draft.dsw.gui.swing.model.events.EventType;

import raf.draft.dsw.gui.swing.model.tabs.TabPaneModel;

import javax.swing.*;

public class TabPane extends JTabbedPane implements ISubscriber {
    private TabPaneModel model;
    public TabPane(TabPaneModel model) {
        this.model = model;
        initialize();
    }
    private void initialize() {
        this.model.addSubscriber(this);
        addChangeListener(new TabPaneChangeListener(this.model));
    }
    public void refresh(){
        removeAll();
        for(Tab tab : model.getOpenedTabs())
            add(tab);
    }

    @Override
    public void update(Object value) {
        if(value instanceof EventModel event) {
            if (event.getType() == EventType.PROJECT_SET)
                refresh();
            if(event.getType() == EventType.TAB_ADD){
                JComponent component = (JComponent) event.getValue();
                add(component);
            }
            if(event.getType() == EventType.TAB_RENAME){
                JComponent component = (JComponent) event.getValue();
                component.revalidate();
                component.repaint();
                int index = indexOfComponent(component);
                setTitleAt(index, component.getName());
            }
            if(event.getType() == EventType.TAB_DELETE){
                JComponent component = (JComponent) event.getValue();
                remove(component);
            }
        }
    }
}
