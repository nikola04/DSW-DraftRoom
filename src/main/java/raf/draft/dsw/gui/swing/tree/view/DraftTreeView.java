package raf.draft.dsw.gui.swing.tree.view;

import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class DraftTreeView extends JTree {
    public DraftTreeView(DefaultTreeModel treeModel) {
        setModel(treeModel);
        DraftTreeCellRenderer renderer = new DraftTreeCellRenderer();
        setCellRenderer(renderer);
        setEditable(true);
    }
}
