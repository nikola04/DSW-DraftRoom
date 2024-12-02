package raf.draft.dsw.gui.swing.view;

import raf.draft.dsw.gui.swing.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.model.events.EventModel;
import raf.draft.dsw.gui.swing.model.events.EventType;
import raf.draft.dsw.gui.swing.model.structures.Building;
import raf.draft.dsw.gui.swing.model.structures.Room;

import javax.swing.*;
import java.awt.*;

public class RoomView extends JPanel implements ISubscriber {
    private final Room room;
    public RoomView(Room room) {
        this.room = room;
        initialize();
    }
    private void initialize(){
        setBackground(Color.WHITE);
        this.setName(this.room.getName());
        this.setLayout(new BorderLayout());
        this.room.addSubscriber(this);
    }

    public Color getColor() {
        if(this.room.getParent() instanceof Building building)
            return building.getColor();
        return Color.WHITE;
    }
    public Room getRoom() {
        return room;
    }

    @Override
    public void update(Object value) {
    }
}
