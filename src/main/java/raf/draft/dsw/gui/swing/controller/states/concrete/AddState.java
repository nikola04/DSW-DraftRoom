package raf.draft.dsw.gui.swing.controller.states.concrete;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.controller.states.State;
import raf.draft.dsw.gui.swing.model.messages.MessageType;
import raf.draft.dsw.gui.swing.model.structures.Room;
import raf.draft.dsw.gui.swing.model.structures.RoomElement;
import raf.draft.dsw.gui.swing.model.structures.elements.ElementFactory;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.gui.swing.view.RoomView;

import javax.swing.*;
import java.awt.*;

public class AddState implements State{
    @Override
    public void handleMouseClick(RoomView roomView, Point p) {
        Room room = roomView.getRoom();
        if(!room.isPointInsideRoom(p)){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("You can only place elements inside room.", MessageType.WARNING);
            return;
        }
        DraftTreeItem roomTreeItem = MainFrame.getInstance().getDraftTree().findTreeItem(room);
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"Bed", "Bath", "Boiler","Sink", "Door","Table","Toilet","Wardrobe","WashingMachine"});
        JPanel box = new JPanel(new BorderLayout());
        box.add(comboBox);
        int selected = JOptionPane.showConfirmDialog(null, box, "Choose Element", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        String type = (String) comboBox.getSelectedItem();
        if(type == null || selected == -1) return;
        String widthStr = JOptionPane.showInputDialog("Enter width:");
        String heightStr = JOptionPane.showInputDialog("Enter height:");
        try {
            int width = Integer.parseInt(widthStr);
            int height = Integer.parseInt(heightStr);
            RoomElement element = ElementFactory.getRoomElement(type, room);
            element.initialize(p.x, p.y, width, height);
            if(!room.isPointInsideRoom(new Point(p.x + width, p.y + height))) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("You can only place elements inside room.", MessageType.WARNING);
                return;
            }
            if(!room.canPlaceElement(element)) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Element is already placed there", MessageType.WARNING);
                return;
            }
            MainFrame.getInstance().getDraftTree().addChild(roomTreeItem, element);
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
