package raf.draft.dsw.gui.swing.controller.states.concrete;

import raf.draft.dsw.gui.swing.controller.states.State;
import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
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
        Rectangle rotatedElement = selectedElement.getRotatedBounds();
        Point corner = new Point(rotatedElement.x + rotatedElement.width, rotatedElement.y + rotatedElement.height);
        Rectangle draggableRect = new Rectangle(corner.x - threshold, corner.y - threshold, threshold * 2, threshold * 2);
        if(!draggableRect.contains(p))
            selectedElement = null;
        else lastPoint = p;
    }
    @Override
    public void handleMouseDrag(RoomView roomView, Point p) {
        if(selectedElement == null || lastPoint == null) return;
        Room room = roomView.getRoom();
        int resizeX = p.x - lastPoint.x;
        int resizeY = p.y - lastPoint.y;
        lastPoint = p;
        int originalWidth = selectedElement.getLogicalWidth();
        int newWidth = originalWidth + resizeX;
        int originalHeight = selectedElement.getLogicalHeight();
        int newHeight = originalHeight + resizeY;

        if(newWidth * selectedElement.getParent().getPxConversionRatio() <= 5) {
            resizeX = 0;
//            if(selectedElement.getRotateRatio() == 0 || selectedElement.getRotateRatio() == 2) // not actually working :(
//                selectedElement.setWidth(selectedElement.getLogicalWidth() + resizeX);
//            else selectedElement.setHeight(selectedElement.getLogicalHeight() + resizeX);
        }
        if(newHeight * selectedElement.getParent().getPxConversionRatio() <= 5) {
            resizeY = 0;
//            if(selectedElement.getRotateRatio() == 0 || selectedElement.getRotateRatio() == 2)
//                selectedElement.setHeight(selectedElement.getLogicalHeight() + resizeY);
//            else selectedElement.setWidth(selectedElement.getLogicalWidth() + resizeY);
        }

        selectedElement.setWidth(originalWidth + resizeX);
        selectedElement.setHeight(originalHeight + resizeY);

        if((selectedElement.getRotatedBounds().x + selectedElement.getRotatedBounds().width > room.getWidth()) ||
                (selectedElement.getRotatedBounds().y + selectedElement.getRotatedBounds().height > room.getHeight())) {
            selectedElement.setWidth(originalWidth);
            selectedElement.setHeight(originalHeight);
            return;
        }

        if (!room.canPlaceElement(selectedElement)) { // checks for overlap
            selectedElement.setWidth(originalWidth);
            selectedElement.setHeight(originalHeight);
            return;
        }
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
