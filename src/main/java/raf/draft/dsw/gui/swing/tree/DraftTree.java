package raf.draft.dsw.gui.swing.tree;

import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.tree.view.DraftTreeView;
import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.structures.ProjectExplorer;

public interface DraftTree {
    void addChild(DraftTreeItem parent);
    void renameNode(DraftTreeItem item);
    void removeNode(DraftTreeItem item);
    DraftTreeView generateTree(ProjectExplorer projectExplorer);
    DraftTreeItem getSelectedNode();
    DraftNode createNode(DraftNode parent);
}