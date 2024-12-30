package raf.draft.dsw.gui.swing.model.structures;

import com.fasterxml.jackson.annotation.JsonTypeName;
import raf.draft.dsw.gui.swing.controller.observer.IPublisher;
import raf.draft.dsw.gui.swing.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.model.events.EventModel;
import raf.draft.dsw.gui.swing.model.events.EventType;
import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.nodes.DraftNodeComposite;
import raf.draft.dsw.gui.swing.model.utils.ColorUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@JsonTypeName("Building")
public class Building extends DraftNodeComposite implements IPublisher {
    private final List<ISubscriber> subscribers = new ArrayList<>();
    private String color;
    public Building(String name, DraftNode parent) {
        super(name, parent);
        initialize();
    }
    public Building() {
        super(null, null);
    }
    private void initialize() {
        Random rnd = new Random();
        this.color = ColorUtil.toHex(new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)));
    }

    public void setName(String name){
        this.name = name;
        super.onAppliedChange();
        publish(new EventModel(EventType.BUILDING_NAME, name));
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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
