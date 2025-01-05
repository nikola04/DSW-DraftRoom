package raf.draft.dsw.gui.swing.controller.commands.concrete;

import raf.draft.dsw.gui.swing.controller.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.model.structures.Room;
import raf.draft.dsw.gui.swing.model.structures.RoomElement;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.view.MainFrame;

import java.util.ArrayList;
import java.util.List;

public class PasteCopiedCommand extends AbstractCommand {
    private final Room room;
    private final DraftTreeItem roomTreeItem;
    private final List<RoomElement> clones;

    public PasteCopiedCommand(Room room, List<RoomElement> clones) {
        this.room = room;
        this.roomTreeItem = MainFrame.getInstance().getDraftTree().findTreeItem(room);
        this.clones = new ArrayList<>(clones);
    }

    @Override
    public void doCommand() {
        for (RoomElement clone : clones) {
            MainFrame.getInstance().getDraftTree().addChild(roomTreeItem, clone);
            room.addChild(clone);
        }
    }

    @Override
    public void undoCommand() {
        for (RoomElement clone : clones) {
            DraftTreeItem treeItem = MainFrame.getInstance().getDraftTree().findTreeItem(clone);
            MainFrame.getInstance().getDraftTree().removeNode(treeItem);
            room.removeChild(clone);
        }
    }
}
