package raf.draft.dsw.gui.swing.controller.listeners;

import raf.draft.dsw.gui.swing.model.structures.Room;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.gui.swing.view.RoomView;

import java.awt.*;
import java.awt.event.*;

public class RoomMouseListener implements MouseListener, MouseWheelListener, MouseMotionListener {
    RoomView roomView;
    public RoomMouseListener(RoomView room) {
        this.roomView = room;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        MainFrame.getInstance().getProjectView().onMouseClick(roomView, calculateLogicalCoordinates(e.getPoint()));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        MainFrame.getInstance().getProjectView().onMousePress(roomView, calculateLogicalCoordinates(e.getPoint()));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MainFrame.getInstance().getProjectView().onMouseRelease(roomView, calculateLogicalCoordinates(e.getPoint()));
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Room room = roomView.getRoom();
        MainFrame.getInstance().getProjectView().onMouseDrag(roomView, calculateLogicalCoordinates(e.getPoint()));
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        MainFrame.getInstance().getProjectView().onMouseWheel(roomView, e.getPreciseWheelRotation() * (-1));
    }
    private Point calculateLogicalCoordinates(Point p) {
        Room room = roomView.getRoom();
        double scaleFactor = room.getScaleFactor();
        double pxRatio = room.getPxConversionRatio();

        // Calculate translation to center the room
        int translateX = (roomView.getWidth() - (int) (room.getWidth() * pxRatio * scaleFactor)) / 2;
        int translateY = (roomView.getHeight() - (int) (room.getHeight() * pxRatio * scaleFactor)) / 2;

        // Adjust for scaling and translation
        int adjustedX = (int) (((p.getX() - translateX) / (scaleFactor)) / room.getPxConversionRatio());
        int adjustedY = (int) (((p.getY() - translateY) / (scaleFactor)) / room.getPxConversionRatio());
        return new Point(adjustedX, adjustedY);
    }
}
