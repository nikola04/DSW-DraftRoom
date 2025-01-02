package raf.draft.dsw.gui.swing.controller.commands.concrete;

import raf.draft.dsw.gui.swing.controller.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.view.MainFrame;

public class RenameCommand extends AbstractCommand {
    DraftNode node;
    String oldName;
    String newName;
    public RenameCommand(DraftNode node, String oldName, String newName) {
        this.node = node;
        this.oldName = oldName;
        this.newName = newName;
    }
    @Override
    public void doCommand() {
        DraftTreeItem treeItem = MainFrame.getInstance().getDraftTree().findTreeItem(node);
        node.setName(newName);
        MainFrame.getInstance().getDraftTree().renameNode(treeItem, newName);
    }

    @Override
    public void undoCommand() {
        DraftTreeItem treeItem = MainFrame.getInstance().getDraftTree().findTreeItem(node);
        node.setName(oldName);
        MainFrame.getInstance().getDraftTree().renameNode(treeItem, oldName);
    }
}
