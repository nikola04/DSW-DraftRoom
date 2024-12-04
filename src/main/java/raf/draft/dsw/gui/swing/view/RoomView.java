package raf.draft.dsw.gui.swing.view;

import raf.draft.dsw.gui.swing.controller.listeners.RoomMouseListener;
import raf.draft.dsw.gui.swing.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
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

    public void refresh(){
        painters.clear();
        for(DraftNode element: this.room.getChildren()){
            if(element instanceof Bed bed) painters.add(new BedPainter(bed));
        }
        repaint();
    }

    @Override
    public void update(Object value) {
        refresh();
    }
    public Room getRoom() {
        return room;
    }
}
