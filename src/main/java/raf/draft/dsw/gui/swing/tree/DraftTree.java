package raf.draft.dsw.gui.swing.tree;

import raf.draft.dsw.gui.swing.model.structures.RoomElement;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.tree.view.DraftTreeView;
import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.structures.ProjectExplorer;

public interface DraftTree {
    void addChild(DraftTreeItem parent);
    void addChild(DraftTreeItem parent, RoomElement element);
    void renameNode(DraftTreeItem item);
    void removeNode(DraftTreeItem item);
    void removeNodeSilently(DraftTreeItem item);
    DraftTreeItem findTreeItem(DraftNode node);
    DraftTreeView generateTree(ProjectExplorer projectExplorer);
    DraftTreeItem getSelectedNode();
    DraftTreeView getTreeView();
    DraftNode createNode(DraftNode parent);
}