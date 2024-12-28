package raf.draft.dsw.gui.swing.controller.commands.concrete;

import raf.draft.dsw.gui.swing.controller.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.model.structures.RoomElement;
import raf.draft.dsw.gui.swing.view.RoomView;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoveCommand extends AbstractCommand {
    private final RoomView roomView;
    private final List<RoomElement> elements;
    private final Map<RoomElement, Point> originalPoints;
    private final Map<RoomElement, Point> newPoints;
    public MoveCommand(RoomView roomView, List<RoomElement> elements, Map<RoomElement, Point> originalPoints, Map<RoomElement, Point> newPoints) {
        this.roomView = roomView;
        this.elements = new ArrayList<>(elements);
        this.originalPoints = new HashMap<>(originalPoints);
        this.newPoints = new HashMap<>(newPoints);
    }
    @Override
    public void doCommand() {
        for(RoomElement element : elements) {
            Point newPoint = newPoints.get(element);
            element.setX(newPoint.x);
            element.setY(newPoint.y);
        }
        roomView.repaint();
    }

    @Override
    public void undoCommand() {
        for(RoomElement element : elements) {
            Point originalPoint = originalPoints.get(element);
            element.setX(originalPoint.x);
            element.setY(originalPoint.y);
        }
        roomView.repaint();
    }
}
