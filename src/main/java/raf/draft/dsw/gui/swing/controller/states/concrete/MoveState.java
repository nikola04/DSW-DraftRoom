package raf.draft.dsw.gui.swing.controller.states.concrete;

import raf.draft.dsw.gui.swing.controller.states.State;
import raf.draft.dsw.gui.swing.model.structures.RoomElement;
import raf.draft.dsw.gui.swing.view.RoomView;

import java.awt.*;
import java.util.List;

public class MoveState implements State{
    private Point lastPoint;
    @Override
    public void handleMousePress(RoomView roomView, Point p) {
        List<RoomElement> selectedElements = roomView.getRoom().getSelectedElements();
        if(selectedElements.isEmpty()) return;
        lastPoint = p;
    }
    @Override
    public void handleMouseDrag(RoomView roomView, Point p) {
        List<RoomElement> selectedElements = roomView.getRoom().getSelectedElements();
        if(selectedElements.isEmpty()) return;
        lastPoint = p;
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
