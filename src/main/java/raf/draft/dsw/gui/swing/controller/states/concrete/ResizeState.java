package raf.draft.dsw.gui.swing.controller.states.concrete;

import raf.draft.dsw.gui.swing.controller.states.State;
import raf.draft.dsw.gui.swing.model.structures.Room;
import raf.draft.dsw.gui.swing.model.structures.RoomElement;
import raf.draft.dsw.gui.swing.model.structures.elements.Selection;
import raf.draft.dsw.gui.swing.view.RoomView;

import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class ResizeState implements State{
    private RoomElement selectedElement;
    private Point lastPoint;
    @Override
    public void handleMousePress(RoomView roomView, Point p) {
        List<RoomElement> selectedElements = roomView.getRoom().getSelectedElements();
        selectedElement = null;
        if(selectedElements.size() != 1) return;
        selectedElement = selectedElements.getFirst();
        int threshold = 10; // * calculatedValueForScale,
        Point corner = new Point(selectedElement.getX() + selectedElement.getWidth(), selectedElement.getY() + selectedElement.getHeight());
        Rectangle draggableRect = new Rectangle(corner.x - threshold, corner.y - threshold, threshold * 2, threshold * 2);
        if(!draggableRect.contains(p))
            selectedElement = null;
        else lastPoint = p;
    }
    @Override
    public void handleMouseDrag(RoomView roomView, Point p) {
        if(selectedElement == null || lastPoint == null) return;
        int resizeX = p.x - lastPoint.x;
        int resizeY = p.y - lastPoint.y;
        lastPoint = p;
        if(selectedElement.getWidth() + resizeX > 10) selectedElement.setWidth(selectedElement.getWidth() + resizeX);
        if(selectedElement.getHeight() + resizeY > 10) selectedElement.setHeight(selectedElement.getHeight() + resizeY);
        roomView.repaint();
    }
    @Override
    public void handleMouseRelease(RoomView roomView, Point p) {
        selectedElement = null;
        lastPoint = null;
    }

    @Override
    public void handleMouseClick(RoomView roomView, Point p) {
    }

    @Override
    public void handleMouseWheel(RoomView roomView, double preciseRotation) {
    }
}
