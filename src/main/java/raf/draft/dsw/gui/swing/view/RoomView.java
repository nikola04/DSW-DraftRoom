package raf.draft.dsw.gui.swing.view;

import raf.draft.dsw.gui.swing.controller.listeners.RoomMouseListener;
import raf.draft.dsw.gui.swing.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.model.events.EventModel;
import raf.draft.dsw.gui.swing.model.events.EventType;
import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.structures.Room;
import raf.draft.dsw.gui.swing.model.structures.elements.*;
import raf.draft.dsw.gui.swing.view.painters.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
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
        RoomMouseListener mouseListener = new RoomMouseListener(this);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        this.addMouseWheelListener(mouseListener);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform original = g2d.getTransform();
        double scaleFactor = room.getScaleFactor();
        int scaledWidth = (int) (room.getWidth() * scaleFactor);
        int scaledHeight = (int) (room.getHeight() * scaleFactor);

        // Calculate translation to center the scaled room
        int translateX = (getWidth() - scaledWidth) / 2;
        int translateY = (getHeight() - scaledHeight) / 2;
        g2d.translate(translateX, translateY);
        g2d.scale(scaleFactor, scaleFactor);
        g2d.drawRect(0, 0, room.getWidth(), room.getHeight());
        for(ElementPainter p : painters) {
            p.paint(g);
        }
        g2d.setTransform(original);
    }

    public void refresh(){
        painters.clear();
        for(DraftNode element: this.room.getChildren()){
            if(element instanceof Bath bath) painters.add(new BathPainter(bath));
            if(element instanceof Bed bed) painters.add(new BedPainter(bed));
            if(element instanceof Boiler boiler) painters.add(new BoilerPainter(boiler));
            if(element instanceof Door door) painters.add(new DoorPainter(door));
            if(element instanceof Sink sink) painters.add(new SinkPainter(sink));
            if(element instanceof Table table) painters.add(new TablePainter(table));
            if(element instanceof Toilet toilet) painters.add(new ToiletPainter(toilet));
            if(element instanceof Wardrobe wardrobe) painters.add(new WardrobePainter(wardrobe));
            if(element instanceof WashingMachine washingMachine) painters.add(new WashingMachinePainter(washingMachine));
            if(element instanceof Selection selection) painters.add(new SelectionPainter(selection));
        }
        repaint();
    }

    @Override
    public void update(Object value) {
        if(value instanceof EventModel event){
            if(event.getType() == EventType.REPAINT){
                repaint();
                return;
            }
        }
        refresh();
    }
    public Room getRoom() {
        return room;
    }
}
