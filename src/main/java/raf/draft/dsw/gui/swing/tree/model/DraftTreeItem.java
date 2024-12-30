package raf.draft.dsw.gui.swing.tree.model;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.structures.Building;
import raf.draft.dsw.gui.swing.model.structures.Project;
import raf.draft.dsw.gui.swing.model.structures.Room;

import javax.swing.tree.DefaultMutableTreeNode;

public class DraftTreeItem extends DefaultMutableTreeNode {
    private final DraftNode node;
    public DraftTreeItem(DraftNode node) {
        this.node = node;
    }

    @Override
    public String toString() {
        return node.getName();
    }
    public void setName(String name){
        node.setName(name);
    }
    public DraftNode getDraftNode() {
        return node;
    }
}
