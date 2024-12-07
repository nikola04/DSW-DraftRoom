package raf.draft.dsw.gui.swing.controller.states.concrete;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.controller.states.State;
import raf.draft.dsw.gui.swing.model.messages.MessageType;
import raf.draft.dsw.gui.swing.model.structures.Room;
import raf.draft.dsw.gui.swing.model.structures.RoomElement;
import raf.draft.dsw.gui.swing.model.structures.elements.ElementFactory;
import raf.draft.dsw.gui.swing.model.structures.elements.Selection;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.gui.swing.view.RoomView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectState implements State{
    private Point selectionStart;
    private Selection selection;
    @Override
    public void handleMousePress(RoomView roomView, Point p) {
        roomView.getRoom().resetSelected();
        this.selection = new Selection(roomView.getRoom(), null, p.x, p.y, 0, 0);
        selectionStart = p;
    }

    @Override
    public void handleMouseRelease(RoomView roomView, Point p) {
        List<Point> points = getOrderedSelectionPoints(p);
        if (points == null) return;
        Room room = roomView.getRoom();
        this.selection = null;
        room.setSelectionElement(null);
        roomView.getRoom().resetSelected();
        Point p1 = points.get(0);
        Point p2 = points.get(1);
        List<RoomElement> selectedElements = room.overlappedElements(p1.x, p1.y, p2.x - p1.x, p2.y - p1.y);
        room.setSelectedElements(selectedElements);
    }

    @Override
    public void handleMouseDrag(RoomView roomView, Point p) {
        List<Point> points = getOrderedSelectionPoints(p);
        if (points == null) return;
        if(roomView.getRoom().getSelectionElement() == null) roomView.getRoom().setSelectionElement(this.selection);
        Point p1 = points.get(0);
        Point p2 = points.get(1);
        this.selection.initialize(p1.x, p1.y, p2.x - p1.x, p2.y - p1.y);
        roomView.repaint();
    }
    private List<Point> getOrderedSelectionPoints(Point p){
        if (selectionStart != null) {
            Point p1 = new Point(Math.min(selectionStart.x, p.x), Math.min(selectionStart.y, p.y)); // Top-left corner
            Point p2 = new Point(Math.max(selectionStart.x, p.x), Math.max(selectionStart.y, p.y)); // Bottom-right corner
            return Arrays.asList(p1, p2);
        }
        return null;
    }
    @Override
    public void handleMouseClick(RoomView roomView, Point p) {
    }

    @Override
    public void handleMouseWheel(RoomView roomView, double preciseRotation) {
    }
}
