package raf.draft.dsw.gui.swing.view;

import raf.draft.dsw.gui.swing.model.structures.Building;
import raf.draft.dsw.gui.swing.model.structures.Room;

import javax.swing.*;
import java.awt.*;

public class Tab extends JPanel {
    private final Room room;
    public Tab(Room room) {
        this.room = room;
        initialize();
    }
    private void initialize(){
        setBackground(Color.WHITE);
        this.setName(this.room.getName());
        this.setLayout(new BorderLayout());
    }

    public Color getColor() {
        if(this.room.getParent() instanceof Building building)
            return building.getColor();
        return Color.WHITE;
    }
    public Room getRoom() {
        return room;
    }
}
