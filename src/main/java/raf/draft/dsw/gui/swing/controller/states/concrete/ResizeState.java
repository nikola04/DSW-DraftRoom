package raf.draft.dsw.gui.swing.controller.states.concrete;

import raf.draft.dsw.gui.swing.controller.states.State;
import raf.draft.dsw.gui.swing.model.structures.Room;
import raf.draft.dsw.gui.swing.model.structures.RoomElement;
import raf.draft.dsw.gui.swing.view.RoomView;

import java.awt.*;
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
        int threshold = Math.max((int)(10 / roomView.getRoom().getPxConversionRatio()), 1);
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
        double pxRatio = room.getPxConversionRatio();
        int rotateRatio = Math.abs(selectedElement.getRotateRatio());
        boolean isRotated = rotateRatio == 1 || rotateRatio == 3;
        int originalWidth = selectedElement.getLogicalWidth();
        int originalHeight = selectedElement.getLogicalHeight();
        Rectangle rotatedBounds = selectedElement.getRotatedBounds();

        if((rotatedBounds.width + resizeX) * pxRatio <= 5) resizeX = 0;
        if((rotatedBounds.height + resizeY) * pxRatio <= 5) resizeY = 0;
        if(resizeX == 0 && resizeY == 0) return;
        if(isRotated) {
            selectedElement.setHeight(originalHeight + resizeX);
            selectedElement.setWidth(originalWidth + resizeY);
        }else {
            selectedElement.setWidth(originalWidth + resizeX);
            selectedElement.setHeight(originalHeight + resizeY);
        }

        if (room.isNotInsideRoom(selectedElement) || !room.canPlaceElement(selectedElement)) { // checks for overlap or room bounds
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
