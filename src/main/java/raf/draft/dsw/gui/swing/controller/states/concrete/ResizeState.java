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
        int threshold = (int)(10 / roomView.getRoom().getPxConversionRatio());
        Point corner = new Point(selectedElement.getLogicalX() + selectedElement.getLogicalWidth(), selectedElement.getLogicalY() + selectedElement.getLogicalHeight());
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
        if((selectedElement.getLogicalWidth() + resizeX) * selectedElement.getParent().getPxConversionRatio() > 5) // check if width is more than 5px
            selectedElement.setWidth(selectedElement.getLogicalWidth() + resizeX);
        if((selectedElement.getLogicalHeight() + resizeY) * selectedElement.getParent().getPxConversionRatio() > 5) // check if height is more than 5px
            selectedElement.setHeight(selectedElement.getLogicalHeight() + resizeY);
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
