package raf.draft.dsw.gui.swing.view;

import raf.draft.dsw.gui.swing.controller.listeners.TabPaneChangeListener;
import raf.draft.dsw.gui.swing.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.model.events.EventModel;
import raf.draft.dsw.gui.swing.model.events.EventType;

import raf.draft.dsw.gui.swing.model.tabs.TabPaneModel;

import javax.swing.*;

public class TabPane extends JTabbedPane implements ISubscriber {
    private final TabPaneModel model;
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
        int ind = 0;
        for(RoomView tab : model.getOpenedTabs()) {
            add(tab);
            setBackgroundAt(ind++, tab.getRoom().getColor());
        }
    }

    @Override
    public void update(Object value) {
        if(value instanceof EventModel event) {
            if (event.getType() == EventType.PROJECT_SET)
                refresh();
            if(event.getType() == EventType.TAB_ADD){
                RoomView component = (RoomView) event.getValue();
                add(component);
                int index = indexOfComponent(component);
                setBackgroundAt(index, component.getRoom().getColor());
            }
            if(event.getType() == EventType.TAB_RENAME){
                JComponent component = (JComponent) event.getValue();
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
