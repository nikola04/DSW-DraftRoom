package raf.draft.dsw.gui.swing.model.tabs;

import raf.draft.dsw.gui.swing.controller.observer.IPublisher;
import raf.draft.dsw.gui.swing.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.model.events.EventModel;
import raf.draft.dsw.gui.swing.model.events.EventType;
import raf.draft.dsw.gui.swing.model.structures.Project;
import raf.draft.dsw.gui.swing.model.structures.Room;
import raf.draft.dsw.gui.swing.view.Tab;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TabPaneModel implements IPublisher {
    private final List<ISubscriber> subscribers;
    private Project project;
    private final List<Tab> openedTabs;
    private Tab activeTab;

    public TabPaneModel() {
        subscribers = new ArrayList<>();
        openedTabs = new ArrayList<>();
        project = null;
        activeTab = null;
    }

    public void setProject(Project project) {
        this.project = project;
        publish(project);
        openedTabs.clear();
        if(project != null)
            for(Room room : project.getAllRooms()){
                Tab newTab = new Tab(room);
                openedTabs.add(newTab);
            }
        setActiveTab(null);
        publish(new EventModel(EventType.PROJECT_SET, project));
    }
    public void removeTabByRoom(Room room) {
        for(Tab tab : openedTabs){
            if(!tab.getRoom().equals(room)) continue;
            openedTabs.remove(tab);
            publish(new EventModel(EventType.TAB_DELETE, tab));
            break;
        }
    }
    public void addTab(Room room) {
        Tab newTab = new Tab(room);
        openedTabs.add(newTab);
        publish(new EventModel(EventType.TAB_ADD, newTab));
    }
    public void renameTabByRoom(Room room, String newName) {
        for(Tab tab : openedTabs){
            if(!tab.getRoom().equals(room)) continue;
            if(tab.getName().equals(newName)) return;
            tab.setName(newName);
            publish(new EventModel(EventType.TAB_RENAME, tab));
        }
    }
    public void setActiveTab(Tab tab) {
        activeTab = tab;
        publish(new EventModel(EventType.TAB_SELECTED, activeTab));
    }
    @Override
    public void addSubscriber(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void publish(Object value) {
        for (ISubscriber subscriber : subscribers) {
            subscriber.update(value);
        }
    }

    public List<Tab> getOpenedTabs() {
        return openedTabs;
    }

    public Tab getActiveTab() {
        return activeTab;
    }

    public Project getProject() {
        return project;
    }
}
