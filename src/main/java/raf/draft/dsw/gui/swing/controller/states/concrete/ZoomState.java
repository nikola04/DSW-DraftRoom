package raf.draft.dsw.gui.swing.controller.states.concrete;

import raf.draft.dsw.gui.swing.controller.states.State;
import raf.draft.dsw.gui.swing.view.RoomView;

import java.awt.*;

public class ZoomState implements State {
    @Override
    public void handleMouseWheel(RoomView roomView, double preciseRotation) {
        double zoomFactorPerStep = 0.1; // sensitivity
        double zoomChange = 1 + preciseRotation * zoomFactorPerStep;
        if (zoomChange > 0) {
            double scaleFactor = roomView.getRoom().getScaleFactor();
            scaleFactor *= zoomChange;
            roomView.getRoom().setScaleFactor(scaleFactor);
            roomView.repaint();
        }
    }

    @Override
    public void handleMouseClick(RoomView roomView, Point p) {
    }
    @Override
    public void handleMousePress(RoomView roomView, Point p) {
    }

    @Override
    public void handleMouseRelease(RoomView roomView, Point p) {
    }

    @Override
    public void handleMouseDrag(RoomView roomView, Point p) {
    }
}
