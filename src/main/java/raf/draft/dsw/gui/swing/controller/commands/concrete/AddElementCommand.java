package raf.draft.dsw.gui.swing.controller.commands.concrete;

import raf.draft.dsw.gui.swing.controller.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.model.structures.Room;
import raf.draft.dsw.gui.swing.model.structures.RoomElement;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.view.MainFrame;

public class AddElementCommand extends AbstractCommand {
    private final Room room;
    private final DraftTreeItem roomTreeItem;
    private final RoomElement element;

    public AddElementCommand(Room room, RoomElement element) {
        this.room = room;
        this.roomTreeItem = MainFrame.getInstance().getDraftTree().findTreeItem(room);;
        this.element = element;
    }

    @Override
    public void doCommand() {
        MainFrame.getInstance().getDraftTree().addChild(roomTreeItem, element);
        room.addChild(element);
    }

    @Override
    public void undoCommand() {
        DraftTreeItem treeItem = MainFrame.getInstance().getDraftTree().findTreeItem(element);
        MainFrame.getInstance().getDraftTree().removeNode(treeItem);
    }
}
