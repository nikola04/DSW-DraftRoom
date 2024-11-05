package raf.draft.dsw.gui.swing.tree;

import raf.draft.dsw.gui.swing.controller.messagegenerator.ConsoleLogger;
import raf.draft.dsw.gui.swing.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.tree.view.DraftTreeView;
import raf.draft.dsw.model.messages.Message;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import java.time.LocalDateTime;

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
            return new Project(name, "", "", parent);
        return null;
    }

    @Override
    public void addChild(DraftTreeItem parent, String nodeName) {
        if(!(parent.getDraftNode() instanceof DraftNodeComposite))
            return;
        DraftNode child = createNode(nodeName, parent.getDraftNode());
        if(child == null) {
            new ConsoleLogger().log(new Message("Parent is not valid parent", MessageType.WARNING, LocalDateTime.now()));
            return;
        }
        parent.add(new DraftTreeItem(child));
        parent.getDraftNode().addChild(child);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void removeNode(DraftTreeItem item) {
        if(item.getParent() == null)
            return;
        item.removeFromParent();
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }
}
