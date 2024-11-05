package raf.draft.dsw.gui.swing.tree;

import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.tree.view.DraftTreeView;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.structures.ProjectExplorer;

public interface DraftTree {
    void addChild(DraftTreeItem newNode, DraftNode parent);
    void removeNode(DraftNode node);
    DraftTreeView generateTree(ProjectExplorer projectExplorer);
    DraftTreeItem getSelectedNode();
    DraftNode createNode(String nodeName, DraftNode parent);
}