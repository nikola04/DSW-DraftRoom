package raf.draft.dsw.gui.swing.controller.states.concrete;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.controller.states.State;
import raf.draft.dsw.gui.swing.model.messages.MessageType;
import raf.draft.dsw.gui.swing.view.RoomView;

import javax.swing.*;
import java.awt.*;

public class EditRoomState implements State {
    @Override
    public void handleMouseClick(RoomView roomView, Point p) {
        if(roomView.getRoom().isDimensionsSet()) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Dimensions are already set", MessageType.INFO);
            return;
        }
        String widthStr = JOptionPane.showInputDialog("Enter width:");
        String heightStr = JOptionPane.showInputDialog("Enter height:");
        try {
            int width = Integer.parseInt(widthStr);
            int height = Integer.parseInt(heightStr);
            roomView.getRoom().setDimensions(width, height, roomView.getWidth(), roomView.getHeight());
        } catch (NumberFormatException ex) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Enter valid integers", MessageType.ERROR);
        }
    }

    @Override
    public void handleMouseWheel(RoomView roomView, double preciseRotation) {
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
