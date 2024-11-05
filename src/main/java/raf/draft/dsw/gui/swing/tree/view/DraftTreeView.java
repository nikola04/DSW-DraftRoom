package raf.draft.dsw.gui.swing.tree.view;

import raf.draft.dsw.gui.swing.tree.controller.DraftTreeCellEditor;
import raf.draft.dsw.gui.swing.tree.controller.DraftTreeSelectionListener;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;

import javax.swing.*;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;

public class DraftTreeView extends JTree {
    public DraftTreeView(DefaultTreeModel treeModel) {
        setModel(treeModel);
        DraftTreeCellRenderer renderer = new DraftTreeCellRenderer();
        addTreeSelectionListener(new DraftTreeSelectionListener());
        setCellEditor(new DraftTreeCellEditor(this, renderer));
        setCellRenderer(renderer);
        setEditable(true);
    }
}
