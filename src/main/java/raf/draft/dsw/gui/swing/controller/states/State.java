package raf.draft.dsw.gui.swing.controller.states;

import raf.draft.dsw.gui.swing.view.RoomView;

import java.awt.*;
import java.awt.event.MouseEvent;

public interface State {
    void handleMouseClick(RoomView roomView, Point p);
}