package raf.draft.dsw.gui.swing.controller.states.concrete;

import raf.draft.dsw.gui.swing.controller.states.State;
import raf.draft.dsw.gui.swing.model.structures.Room;
import raf.draft.dsw.gui.swing.model.structures.RoomElement;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.gui.swing.view.RoomView;

import java.awt.*;
import java.util.List;

public class DeleteState implements State {
    @Override
    public void handleMouseClick(RoomView roomView, Point p) {
        Room room = roomView.getRoom();
        List<RoomElement> selected = room.overlappedElements(new Rectangle(p.x, p.y, 0, 0));
        if(selected.isEmpty()) return;
        RoomElement selectedElement = selected.getFirst();
        DraftTreeItem treeItem = MainFrame.getInstance().getDraftTree().findTreeItem(selectedElement);
        MainFrame.getInstance().getDraftTree().removeNode(treeItem);
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
