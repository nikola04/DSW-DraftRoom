package raf.draft.dsw.gui.swing.controller.listeners;

import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.gui.swing.view.RoomView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class RoomMouseListener implements MouseListener, MouseMotionListener {
    RoomView roomView;
    public RoomMouseListener(RoomView room) {
        this.roomView = room;
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        double scaleFactor = roomView.getRoom().getScaleFactor();
        Point p = new Point(e.getX() / (int) scaleFactor, e.getY() / (int) scaleFactor);
        MainFrame.getInstance().getProjectView().onMouseClick(roomView, p);
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
}
