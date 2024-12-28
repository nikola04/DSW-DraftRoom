package raf.draft.dsw.gui.swing.controller.commands.concrete;

import raf.draft.dsw.gui.swing.controller.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.model.structures.Room;
import raf.draft.dsw.gui.swing.model.structures.RoomElement;

import java.util.ArrayList;
import java.util.List;

public class RotateCommand extends AbstractCommand {
    private final int rotation;
    private final Room room;
    private final List<RoomElement> elements;

    public RotateCommand(Room room, int rotation) {
        this.room = room;
        this.elements = new ArrayList<>(room.getSelectedElements());
        this.rotation = rotation;
    }
    @Override
    public void doCommand() {
        room.rotateElements(elements, rotation);
    }

    @Override
    public void undoCommand() {
        room.rotateElements(elements, -rotation);
    }
}
