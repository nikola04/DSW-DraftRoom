package raf.draft.dsw.gui.swing.controller.states.concrete;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.controller.commands.concrete.MoveCommand;
import raf.draft.dsw.gui.swing.controller.states.State;
import raf.draft.dsw.gui.swing.model.structures.Room;
import raf.draft.dsw.gui.swing.model.structures.RoomElement;
import raf.draft.dsw.gui.swing.view.RoomView;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MoveState implements State{
    private Point lastPoint;
    private final Map<RoomElement, Point> originalPoints = new HashMap<>();
    private final Map<RoomElement, Point> newPoints = new HashMap<>();
    private List<RoomElement> elements = new ArrayList<>();
    private boolean isMovingRoom = false;
    @Override
    public void handleMousePress(RoomView roomView, Point p) {
        Room room = roomView.getRoom();
        elements = room.getSelectedElements();
        lastPoint = p;
        if(elements.isEmpty()) isMovingRoom = true;
        if(!isMovingRoom)
            for(RoomElement e : elements) {
                originalPoints.put(e, new Point(e.getLogicalX(), e.getLogicalY()));
                newPoints.put(e, new Point(e.getLogicalX(), e.getLogicalY()));
            }
        else elements = room.getElements();
    }
    @Override
    public void handleMouseDrag(RoomView roomView, Point p) {
        Room room = roomView.getRoom();
        boolean stopOnFirstHit = isMovingRoom;
        if(elements.isEmpty()) return;
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
            int newX;
            boolean isNotBlocked = room.canPlaceElement(element);
            if (moveX < 0 && (rotatedBounds.getMinX() + moveX) * pxRatio <= stickyOffset) {
                if(isRotated) newX = (height - width) / 2;
                else newX = 0;
            } else if (moveX > 0 && (rotatedBounds.getMaxX() + moveX) * pxRatio >= room.getWidth() * pxRatio - stickyOffset) {
                if(isRotated) newX = room.getWidth() - rotatedBounds.width + (height - width) / 2;
                else newX = room.getWidth() - rotatedBounds.width;
            } else newX = originalX + moveX;
            element.setX(newX);
            if (isNotBlocked && !room.canPlaceElement(element)) {
                element.setX(originalX); // Revert X if overlaps
                newX = originalX;
            }
            // Handle Y sticking
            int newY;
            if (moveY < 0 && (rotatedBounds.getMinY() + moveY) * pxRatio <= stickyOffset) {
                if(isRotated) newY = (width - height) / 2;
                else newY = 0;
            } else if (moveY > 0 && (rotatedBounds.getMaxY() + moveY) * pxRatio >= room.getHeight() * pxRatio - stickyOffset) {
                if(isRotated) newY = room.getHeight() - rotatedBounds.height + (width - height) / 2;
                else newY = room.getHeight() - rotatedBounds.height;
            } else newY = originalY + moveY;
            element.setY(newY);
            if (isNotBlocked && !room.canPlaceElement(element)) {
                element.setY(originalY); // Revert Y if overlaps
                newY = originalY;
            }
            newPoints.put(element, new Point(newX, newY));
        }
        roomView.repaint();
    }
    @Override
    public void handleMouseRelease(RoomView roomView, Point p) {
        if(isMovingRoom || elements.isEmpty()) return;
        MoveCommand moveCommand = new MoveCommand(roomView, elements, originalPoints, newPoints);
        ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(moveCommand);
        lastPoint = null;
        originalPoints.clear();
        newPoints.clear();
    }

    @Override
    public void handleMouseClick(RoomView roomView, Point p) {
    }

    @Override
    public void handleMouseWheel(RoomView roomView, double preciseRotation) {
    }
}
