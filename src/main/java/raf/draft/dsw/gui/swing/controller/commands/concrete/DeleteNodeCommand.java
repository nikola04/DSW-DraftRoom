package raf.draft.dsw.gui.swing.controller.commands.concrete;

import raf.draft.dsw.gui.swing.controller.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.nodes.DraftNodeComposite;
import raf.draft.dsw.gui.swing.model.structures.RoomElement;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.view.MainFrame;

import java.util.ArrayList;
import java.util.List;

public class DeleteNodeCommand extends AbstractCommand {
    private final List<DraftNode> children;
    private final DraftNodeComposite parent;
    public DeleteNodeCommand(DraftNode child) {
        this.children = new ArrayList<>();
        this.children.add(child);
        this.parent = (DraftNodeComposite) child.getParent();
    }
    public DeleteNodeCommand(DraftNode child, DraftNodeComposite parent) {
        this.children = new ArrayList<>();
        this.children.add(child);
        this.parent = parent;
    }
    public DeleteNodeCommand(List<RoomElement> elements, DraftNodeComposite parent) {
        this.children = new ArrayList<>(elements);
        this.parent = parent;
    }
    @Override
    public void doCommand() {
        for(DraftNode node : children) {
            DraftTreeItem treeItem = MainFrame.getInstance().getDraftTree().findTreeItem(node);
            MainFrame.getInstance().getDraftTree().removeNode(treeItem);
            parent.removeChild(node);
        }
    }

    @Override
    public void undoCommand() {
        DraftTreeItem treeItem = MainFrame.getInstance().getDraftTree().findTreeItem(parent);
        for(DraftNode node : children) {
            MainFrame.getInstance().getDraftTree().addChild(treeItem, node);
            parent.addChild(node);
        }
    }
}
