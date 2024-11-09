package raf.draft.dsw.gui.swing.model.structures;

import raf.draft.dsw.gui.swing.controller.observer.IPublisher;
import raf.draft.dsw.gui.swing.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.model.events.EventModel;
import raf.draft.dsw.gui.swing.model.events.EventType;
import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.nodes.DraftNodeComposite;

import java.util.ArrayList;
import java.util.List;

public class Project extends DraftNodeComposite implements IPublisher {
    List<ISubscriber> subscribers = new ArrayList<>();
    private String author;
    private String path;

    public Project(String name, DraftNode parent) {
        super(name, parent);
        this.author = "/";
        this.path = "/";
    }
    public void setName(String newName){
        this.name = newName;
        publish(new EventModel(EventType.PROJECT_NAME, name));
    }
    public void setAuthor(String author) {
        this.author = author;
        publish(new EventModel(EventType.PROJECT_AUTHOR, author));
    }
    public void setPath(String path) {
        this.path = path;
    }

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
