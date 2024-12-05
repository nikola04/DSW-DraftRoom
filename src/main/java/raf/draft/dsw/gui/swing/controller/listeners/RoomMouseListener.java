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
        Room room = roomView.getRoom();
        double scaleFactor = room.getScaleFactor();
        int scaledWidth = (int) (room.getWidth() * scaleFactor);
        int scaledHeight = (int) (room.getHeight() * scaleFactor);
        int translateX = (roomView.getWidth() - scaledWidth) / 2;
        int translateY = (roomView.getHeight() - scaledHeight) / 2;
        int adjustedX = e.getX() - translateX;
        int adjustedY = e.getY() - translateY;

        int logicalX = (int) (adjustedX / scaleFactor);
        int logicalY = (int) (adjustedY / scaleFactor);
        MainFrame.getInstance().getProjectView().onMouseClick(roomView, new Point(logicalX, logicalY));
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        MainFrame.getInstance().getProjectView().onMouseWheel(roomView, e.getPreciseWheelRotation() * (-1));
    }
}
