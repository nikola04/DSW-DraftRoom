package raf.draft.dsw.core;

import raf.draft.dsw.gui.swing.model.structures.Project;
import raf.draft.dsw.gui.swing.model.structures.RoomElement;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.tree.view.DraftTreeView;
import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.structures.ProjectExplorer;

public interface DraftTree {
    void addChild(DraftTreeItem parent);
    void addChild(DraftTreeItem parent, DraftNode element);
    void renameNode(DraftTreeItem item);
    void removeNode(DraftTreeItem item);
    void loadProject(Project node);
    DraftTreeItem findTreeItem(DraftNode node);
    DraftTreeView generateTree(ProjectExplorer projectExplorer);
    DraftTreeItem getSelectedNode();
    DraftNode createNode(DraftNode parent);
}