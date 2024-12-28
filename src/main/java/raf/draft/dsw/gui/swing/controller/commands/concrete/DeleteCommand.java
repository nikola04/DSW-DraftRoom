package raf.draft.dsw.gui.swing.controller.commands.concrete;

import raf.draft.dsw.gui.swing.controller.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.model.structures.Room;
import raf.draft.dsw.gui.swing.model.structures.RoomElement;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.view.MainFrame;

import java.util.ArrayList;
import java.util.List;

public class DeleteCommand extends AbstractCommand {
    private final List<RoomElement> roomElements;
    private final Room room;
    private final DraftTreeItem roomTreeItem;
    public DeleteCommand(RoomElement roomElement, Room room) {
        this.roomElements = new ArrayList<>();
        this.roomElements.add(roomElement);
        this.room = room;
        this.roomTreeItem = MainFrame.getInstance().getDraftTree().findTreeItem(room);;
    }
    public DeleteCommand(List<RoomElement> roomElements, Room room) {
        this.roomElements = new ArrayList<>(roomElements);
        this.room = room;
        this.roomTreeItem = MainFrame.getInstance().getDraftTree().findTreeItem(room);;
    }
    @Override
    public void doCommand() {
        for(RoomElement roomElement : roomElements) {
            DraftTreeItem treeItem = MainFrame.getInstance().getDraftTree().findTreeItem(roomElement);
            MainFrame.getInstance().getDraftTree().removeNode(treeItem);
        }
    }

    @Override
    public void undoCommand() {
        for(RoomElement roomElement : roomElements) {
            MainFrame.getInstance().getDraftTree().addChild(roomTreeItem, roomElement);
            room.addChild(roomElement);
        }
    }
}
