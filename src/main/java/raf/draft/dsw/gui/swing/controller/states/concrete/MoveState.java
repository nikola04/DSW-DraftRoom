package raf.draft.dsw.gui.swing.controller.states.concrete;

import raf.draft.dsw.gui.swing.controller.states.State;
import raf.draft.dsw.gui.swing.model.structures.Room;
import raf.draft.dsw.gui.swing.model.structures.RoomElement;
import raf.draft.dsw.gui.swing.view.RoomView;

import java.awt.*;
import java.util.List;

public class MoveState implements State{
    private Point lastPoint;
    @Override
    public void handleMousePress(RoomView roomView, Point p) {
        lastPoint = p;
    }
    @Override
    public void handleMouseDrag(RoomView roomView, Point p) {
        Room room = roomView.getRoom();
        List<RoomElement> elements = room.getSelectedElements();
        boolean stopOnFirstHit = false;
        if(elements.isEmpty()) {
            elements = room.getElements();
            stopOnFirstHit = true;
        }
        int moveX = p.x - lastPoint.x;
        int moveY = p.y - lastPoint.y;
        lastPoint = p;
        if(stopOnFirstHit)
            for(RoomElement element : elements) {
                Rectangle rotatedBounds = element.getRotatedBounds();
                if((moveX <= 0 && rotatedBounds.getMinX() <= 0) || (moveY <= 0 && rotatedBounds.getMinY() <= 0)) return; // element is fixed no need to move any other element
                if((moveX >= 0 && rotatedBounds.getMaxX() >= room.getWidth()) || (moveY >= 0 && rotatedBounds.getMaxY() >= room.getWidth())) return; // element is fixed to right
            }
        for(RoomElement element : elements) {
            int originalX = element.getLogicalX();
            int originalY = element.getLogicalY();
            int width = element.getLogicalWidth();
            int height = element.getLogicalHeight();
            int rotateRatio = Math.abs(element.getRotateRatio());
            double pxRatio = room.getPxConversionRatio();
            boolean isRotated = (rotateRatio == 1 || rotateRatio == 3);
            int stickyOffset = stopOnFirstHit ? 0 : 10;
            Rectangle rotatedBounds = element.getRotatedBounds();

            // Handle X sticking
            boolean isNotBlocked = room.canPlaceElement(element);
            if (moveX < 0 && (rotatedBounds.getMinX() + moveX) * pxRatio <= stickyOffset) {
                if(isRotated) element.setX((height - width) / 2);
                else element.setX(0);
            } else if (moveX > 0 && (rotatedBounds.getMaxX() + moveX) * pxRatio >= room.getWidth() * pxRatio - stickyOffset) {
                if(isRotated) element.setX(room.getWidth() - rotatedBounds.width + (height - width) / 2);
                else element.setX(room.getWidth() - rotatedBounds.width);
            } else element.setX(originalX + moveX);
            if (isNotBlocked && !room.canPlaceElement(element)) element.setX(originalX); // Revert X if overlaps

            // Handle Y sticking
            if (moveY < 0 && (rotatedBounds.getMinY() + moveY) * pxRatio <= stickyOffset) {
                if(isRotated) element.setY((width - height) / 2);
                else element.setY(0);
            } else if (moveY > 0 && (rotatedBounds.getMaxY() + moveY) * pxRatio >= room.getHeight() * pxRatio - stickyOffset) {
                if(isRotated) element.setY(room.getHeight() - rotatedBounds.height + (width - height) / 2);
                else element.setY(room.getHeight() - rotatedBounds.height);
            } else element.setY(originalY + moveY);
            if (isNotBlocked && !room.canPlaceElement(element)) element.setY(originalY); // Revert Y if overlaps
        }
        roomView.repaint();
    }
    @Override
    public void handleMouseRelease(RoomView roomView, Point p) {
        lastPoint = null;
    }

    @Override
    public void handleMouseClick(RoomView roomView, Point p) {
    }

    @Override
    public void handleMouseWheel(RoomView roomView, double preciseRotation) {
    }
}
