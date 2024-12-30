package raf.draft.dsw.gui.swing.model.structures;

import com.fasterxml.jackson.annotation.JsonIgnore;
import raf.draft.dsw.gui.swing.controller.observer.IPublisher;
import raf.draft.dsw.gui.swing.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.model.events.EventModel;
import raf.draft.dsw.gui.swing.model.events.EventType;
import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.nodes.DraftNodeComposite;
import raf.draft.dsw.gui.swing.view.MainFrame;

import java.util.ArrayList;
import java.util.List;

public class Project extends DraftNodeComposite implements IPublisher {
    private final List<ISubscriber> subscribers = new ArrayList<>();
    private String author;
    private String path;
    private boolean changed;

    public Project(String name, DraftNode parent) {
        super(name, parent);
        this.author = "/";
        this.path = null;
        this.changed = true;
    }

    public Project() {
        super(null, null);
        this.author = null;
        this.path = null;
        this.changed = true;
    }

    public void setName(String newName){
        this.name = newName;
        this.changed = true;
        publish(new EventModel(EventType.PROJECT_NAME, name));
    }
    public void setAuthor(String author) {
        this.author = author;
        this.changed = true;
        publish(new EventModel(EventType.PROJECT_AUTHOR, author));
    }
    public void setPath(String path) {
        this.path = path;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    @JsonIgnore
    public ArrayList<Room> getAllRooms() {
        ArrayList<Room> rooms = new ArrayList<>();
        for (DraftNode node : getChildren()) {
            if(node instanceof Building building) {
                for (DraftNode buildingNode : building.getChildren())
                    if (buildingNode instanceof Room room)
                        rooms.add(room);
            }
            if(node instanceof Room room)
                rooms.add(room);
        }
        return rooms;
    }

    public String getAuthor() {
        return author;
    }

    public String getPath() {
        return path;
    }

    @JsonIgnore
    public boolean isChanged() {
        return changed;
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
}
