package raf.draft.dsw.gui.swing.controller.commands.concrete;

import raf.draft.dsw.gui.swing.controller.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.model.structures.RoomElement;
import raf.draft.dsw.gui.swing.view.RoomView;

import java.awt.*;

public class ResizeCommand extends AbstractCommand {
    private final RoomView roomView;
    private final RoomElement roomElement;
    private final Dimension originalSize, newSize;
    public ResizeCommand(RoomView roomView, RoomElement roomElement, Dimension originalSize, Dimension newSize) {
        this.roomView = roomView;
        this.roomElement = roomElement;
        this.originalSize = originalSize;
        this.newSize = newSize;
    }

    @Override
    public void doCommand() {
        roomElement.setWidth(newSize.width);
        roomElement.setHeight(newSize.height);
        roomView.repaint();
    }

    @Override
    public void undoCommand() {
        roomElement.setWidth(originalSize.width);
        roomElement.setHeight(originalSize.height);
        roomView.repaint();
    }
}
