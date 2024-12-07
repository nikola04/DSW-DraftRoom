package raf.draft.dsw.gui.swing.controller.states.concrete;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.controller.states.State;
import raf.draft.dsw.gui.swing.model.messages.MessageType;
import raf.draft.dsw.gui.swing.model.structures.Room;
import raf.draft.dsw.gui.swing.model.structures.RoomElement;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.gui.swing.view.RoomView;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EditState implements State {
    @Override
    public void handleMouseClick(RoomView roomView, Point p) {
        Room room = roomView.getRoom();
        List<RoomElement> selectedElements = room.overlappedElements(new Rectangle(p.x, p.y, 0, 0));
        if (selectedElements.size() != 1)
            return;
        RoomElement selectedElement = selectedElements.getFirst();

        JTextField xField = new JTextField(String.valueOf(selectedElement.getLogicalX()), 5);
        JTextField yField = new JTextField(String.valueOf(selectedElement.getLogicalY()), 5);
        JTextField widthField = new JTextField(String.valueOf(selectedElement.getLogicalWidth()), 5);
        JTextField heightField = new JTextField(String.valueOf(selectedElement.getLogicalHeight()), 5);

        JSpinner rotateRatioSpinner = new JSpinner(
                new SpinnerNumberModel(Math.abs(selectedElement.getRotateRatio()), 0, 3, 1)
        );

        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.add(new JLabel("X:"));
        panel.add(xField);
        panel.add(new JLabel("Y:"));
        panel.add(yField);
        panel.add(new JLabel("Width:"));
        panel.add(widthField);
        panel.add(new JLabel("Height:"));
        panel.add(heightField);
        panel.add(new JLabel("Rotate Ratio (0-3):"));
        panel.add(rotateRatioSpinner);

        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Enter Element Details",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                int x = Integer.parseInt(xField.getText());
                int y = Integer.parseInt(yField.getText());
                int width = Integer.parseInt(widthField.getText());
                int height = Integer.parseInt(heightField.getText());
                int rotateRatio = (int) rotateRatioSpinner.getValue();

                int originalX = selectedElement.getLogicalX();
                int originalY = selectedElement.getLogicalY();
                int originalWidth = selectedElement.getLogicalWidth();
                int originalHeight = selectedElement.getLogicalHeight();
                int originalRotateRatio = selectedElement.getRotateRatio();


                selectedElement.setX(x);
                selectedElement.setY(y);
                selectedElement.setWidth(width);
                selectedElement.setHeight(height);
                selectedElement.setRotateRatio(rotateRatio);
                if (!room.canPlaceElement(selectedElement) || !room.isInsideRoom(selectedElement)) {
                    selectedElement.setX(originalX);
                    selectedElement.setY(originalY);
                    selectedElement.setWidth(originalWidth);
                    selectedElement.setHeight(originalHeight);
                    selectedElement.setRotateRatio(originalRotateRatio);
                    ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Position values not valid", MessageType.WARNING);
                    return;
                }
                roomView.repaint();
            } catch (NumberFormatException e) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Please enter valid Integers.", MessageType.WARNING);
            }
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
