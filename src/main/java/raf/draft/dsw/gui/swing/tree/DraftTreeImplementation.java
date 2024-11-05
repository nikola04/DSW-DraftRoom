package raf.draft.dsw.gui.swing.tree;

import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.tree.view.DraftTreeView;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.util.Random;

public class DraftTreeImplementation implements DraftTree {
    private DraftTreeView treeView;
    private DefaultTreeModel treeModel;

    @Override
    public DraftTreeView generateTree(ProjectExplorer projectExplorer) {
        DraftTreeItem root = new DraftTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new DraftTreeView(treeModel);
        return treeView;
    }

    public DraftTreeItem getSelectedNode() {
        return (DraftTreeItem) treeView.getLastSelectedPathComponent();
    }

    @Override
    public DraftNode createNode(String name, DraftNode parent){
        if (parent instanceof ProjectExplorer)
            return new Project("Project" + new Random().nextInt(100), "", "", parent);
        return null;
    }

    @Override
    public void addChild(DraftTreeItem newNode, DraftNode parent) {
    }

    @Override
    public void removeNode(DraftNode node) {

    }
}
