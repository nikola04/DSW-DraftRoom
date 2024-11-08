package raf.draft.dsw.gui.swing.view;

import raf.draft.dsw.gui.swing.model.structures.Building;
import raf.draft.dsw.gui.swing.model.structures.Room;

import javax.swing.*;
import java.awt.*;

public class Tab extends JPanel {
    private Room room;
    public Tab(Room room) {
        this.room = room;
        initialize();
    }
    private void initialize(){
        if(this.room.getParent() instanceof Building building) {
            setBackground(building.getColor());
        }
        this.setName(this.room.getName());
        this.setLayout(new BorderLayout());
    }
}
