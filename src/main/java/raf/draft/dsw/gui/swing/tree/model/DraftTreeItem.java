package raf.draft.dsw.gui.swing.tree.model;

import raf.draft.dsw.model.nodes.DraftNode;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

public class DraftTreeItem extends DefaultMutableTreeNode {
    private DraftNode node;
    public DraftTreeItem(DraftNode node) {
        this.node = node;
    }

    @Override
    public String toString() {
        return node.getName();
    }
    public DraftNode getDraftNode() {
        return node;
    }
}
