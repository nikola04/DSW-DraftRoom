package raf.draft.dsw.gui.swing.controller.commands.concrete;

import raf.draft.dsw.gui.swing.controller.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.model.structures.RoomElement;
import raf.draft.dsw.gui.swing.view.RoomView;

public class EditElementCommand extends AbstractCommand {
    RoomView roomView;
    RoomElement roomElement;
    private final int originalX, originalY, originalWidth, originalHeight, originalRotateRatio;
    private final int newX, newY, newWidth, newHeight, newRotateRatio;

    public EditElementCommand(RoomView roomView, RoomElement roomElement, int originalX, int originalY, int originalWidth, int originalHeight, int originalRotateRatio) {
        this.roomView = roomView;
        this.roomElement = roomElement;
        this.originalX = originalX;
        this.originalY = originalY;
        this.originalWidth = originalWidth;
        this.originalHeight = originalHeight;
        this.originalRotateRatio = originalRotateRatio;
        this.newX = roomElement.getLogicalX();
        this.newY = roomElement.getLogicalY();
        this.newWidth = roomElement.getLogicalWidth();
        this.newHeight = roomElement.getLogicalHeight();
        this.newRotateRatio = roomElement.getRotateRatio();
    }

    @Override
    public void doCommand() {
        roomElement.setX(newX);
        roomElement.setY(newY);
        roomElement.setWidth(newWidth);
        roomElement.setHeight(newHeight);
        roomElement.setRotateRatio(newRotateRatio);
        roomView.repaint();
    }

    @Override
    public void undoCommand() {
        roomElement.setX(originalX);
        roomElement.setY(originalY);
        roomElement.setWidth(originalWidth);
        roomElement.setHeight(originalHeight);
        roomElement.setRotateRatio(originalRotateRatio);
        roomView.repaint();
    }
}
