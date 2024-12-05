package raf.draft.dsw.gui.swing.controller.states;

import raf.draft.dsw.gui.swing.view.RoomView;

import java.awt.*;
import java.awt.event.MouseEvent;

public interface State {
    void handleMouseClick(RoomView roomView, Point p);
    void handleMouseWheel(RoomView roomView, double preciseRotation);
    void handleMousePress(RoomView roomView, Point p);
    void handleMouseRelease(RoomView roomView, Point p);
    void handleMouseDrag(RoomView roomView, Point p);
}