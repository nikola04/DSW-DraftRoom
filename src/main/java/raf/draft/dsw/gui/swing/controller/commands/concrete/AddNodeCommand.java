package raf.draft.dsw.gui.swing.controller.commands.concrete;

import raf.draft.dsw.gui.swing.controller.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.nodes.DraftNodeComposite;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.view.MainFrame;

public class AddNodeCommand extends AbstractCommand {
    private final DraftNodeComposite parent;
    private final DraftNode child;

    public AddNodeCommand(DraftNodeComposite parent, DraftNode child) {
        this.parent = parent;
        this.child = child;
    }

    @Override
    public void doCommand() {
        DraftTreeItem parentTreeItem = MainFrame.getInstance().getDraftTree().findTreeItem(parent);
        MainFrame.getInstance().getDraftTree().addChild(parentTreeItem, child);
        parent.addChild(child);
    }

    @Override
    public void undoCommand() {
        DraftTreeItem treeItem = MainFrame.getInstance().getDraftTree().findTreeItem(child);
        MainFrame.getInstance().getDraftTree().removeNode(treeItem);
        parent.removeChild(child);
    }
}
