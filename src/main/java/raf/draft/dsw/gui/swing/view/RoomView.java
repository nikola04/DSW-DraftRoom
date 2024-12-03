package raf.draft.dsw.gui.swing.view;

import raf.draft.dsw.gui.swing.controller.listeners.RoomMouseListener;
import raf.draft.dsw.gui.swing.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.model.events.EventModel;
import raf.draft.dsw.gui.swing.model.events.EventType;
import raf.draft.dsw.gui.swing.model.structures.Room;
import raf.draft.dsw.gui.swing.model.structures.elements.*;
import raf.draft.dsw.gui.swing.view.painters.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RoomView extends JPanel implements ISubscriber {
    private final Room room;
    private final List<ElementPainter> painters = new ArrayList<>();
    public RoomView(Room room) {
        this.room = room;
        initialize();
    }
    private void initialize(){
        setBackground(Color.WHITE);
        this.setName(this.room.getName());
        this.setLayout(new BorderLayout());
        this.room.addSubscriber(this);
        this.addMouseListener(new RoomMouseListener(this));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int roomWidth = (int) (room.getWidth() * room.getScaleFactor());
        int roomHeight = (int) (room.getHeight() * room.getScaleFactor());
        g2d.drawRect((getWidth() - roomWidth) / 2, (getHeight() - roomHeight) / 2, roomWidth, roomHeight);
        for(ElementPainter p : painters) {
            p.paint(g);
        }
    }
    @Override
    public void update(Object value) {
        if(value instanceof EventModel e) {
            if(e.getType() == EventType.BED_ADD)
                painters.add(new BedPainter((Bed) e.getValue()));
            else if(e.getType() == EventType.BATH_ADD)
                painters.add(new BathPainter((Bath) e.getValue()));
            else if(e.getType() == EventType.BOILER_ADD)
                painters.add(new BoilerPainter((Boiler) e.getValue()));
            else if(e.getType() == EventType.DOOR_ADD)
                painters.add(new DoorPainter((Door) e.getValue()));
            else if(e.getType() == EventType.SINK_ADD)
                painters.add(new SinkPainter((Sink) e.getValue()));
            else if(e.getType() == EventType.TABLE_ADD)
                painters.add(new TablePainter((Table) e.getValue()));
            else if(e.getType() == EventType.TOILET_ADD)
                painters.add(new ToiletPainter((Toilet) e.getValue()));
            else if(e.getType() == EventType.WARDROBE_ADD)
                painters.add(new WardrobePainter((Wardrobe) e.getValue()));
            else if(e.getType() == EventType.WASHING_MACHINE_ADD)
                painters.add(new WashingMachinePainter((WashingMachine) e.getValue()));
            repaint();
        }
    }

    public Room getRoom() {
        return room;
    }
}
