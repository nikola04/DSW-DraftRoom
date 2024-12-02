package raf.draft.dsw.gui.swing.model.structures;

import raf.draft.dsw.gui.swing.controller.observer.IPublisher;
import raf.draft.dsw.gui.swing.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.model.events.EventModel;
import raf.draft.dsw.gui.swing.model.events.EventType;
import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.nodes.DraftNodeComposite;
import raf.draft.dsw.gui.swing.model.nodes.DraftNodeLeaf;

import java.util.ArrayList;
import java.util.List;

public class Room extends DraftNodeComposite implements IPublisher {
    private final List<ISubscriber> subscribers = new ArrayList<>();
    private int dimensionXCm;
    private int dimensionYCm;
    public Room(String name, DraftNode parent) {
        super(name, parent);
        this.dimensionXCm = 0;
        this.dimensionYCm = 0;
    }
    public void setDimensions(int dimensionX, int dimensionY) {
        this.dimensionXCm = dimensionX;
        this.dimensionYCm = dimensionY;
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