package raf.draft.dsw.gui.swing.tree;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.core.DraftTree;
import raf.draft.dsw.gui.swing.model.structures.*;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.tree.view.DraftTreeView;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.gui.swing.model.messages.MessageType;
import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.nodes.DraftNodeComposite;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

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
    public DraftNode createNode(DraftNode parent){
        String type = switch (parent) {
            case ProjectExplorer projectExplorer -> "Project";
            case Project project -> {
                String[] options = {"Building", "Room"};
                int selected = JOptionPane.showOptionDialog(null, "Do you want to add Building or Room into Project", "Select Building or Room", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
                if (selected == 0) yield "Building";
                yield "Room";
            }
            case Building building -> "Room";
            default -> null;
        };
        if(type == null) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Error while getting node type", MessageType.ERROR);
            return null;
        }
        String nodeName = JOptionPane.showInputDialog(null,"Enter " + type + " name:", "Enter Name", JOptionPane.PLAIN_MESSAGE);
        if(nodeName == null)
            return null; // no message on cancel
        if(nodeName.isEmpty()){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Node Name cannot be empty", MessageType.ERROR);
            return null;
        }
        return ApplicationFramework.getInstance().getDraftRoomRepository().createNodeFactory(type, nodeName, parent);
    }

    @Override
    public void addChild(DraftTreeItem parent, DraftNode node) {
        addChildrenRecursive(node, parent);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void renameNode(DraftTreeItem item, String nodeName){
        item.setName(nodeName);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void removeNode(DraftTreeItem item) {
        if(item == null || item.getDraftNode() == null) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Please select project, building or room first", MessageType.WARNING);
            return;
        }
        if(item.getDraftNode() instanceof ProjectExplorer) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("You cannot delete project explorer", MessageType.WARNING);
            return;
        }
        item.removeFromParent();
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void loadProject(Project project) {
        DraftTreeItem rootTreeItem = (DraftTreeItem) treeModel.getRoot();
        DraftTreeItem projectTreeItem = addChildrenRecursive(project, rootTreeItem);

        treeView.expandPath(new TreePath(projectTreeItem.getPath()));
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void loadRoomPattern(Room room) {
        DraftTreeItem roomTreeItem = findTreeItem(room);
        for(DraftNode element : room.getElements()){
            DraftTreeItem childTreeItem = new DraftTreeItem(element);
            roomTreeItem.add(childTreeItem);
        }

        treeView.expandPath(new TreePath(roomTreeItem.getPath()));
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    private DraftTreeItem addChildrenRecursive(DraftNode node, DraftTreeItem parent){
        DraftTreeItem childTreeItem = new DraftTreeItem(node);
        parent.add(childTreeItem);
        if(node instanceof DraftNodeComposite composite)
            for(DraftNode child : composite.getChildren())
                addChildrenRecursive(child, childTreeItem);
        return childTreeItem;
    }

    public DraftTreeItem findTreeItem(DraftNode node) {
        TreeNode root = (TreeNode) treeModel.getRoot();
        return findTreeItemRecursive(root, node);
    }

    private DraftTreeItem findTreeItemRecursive(TreeNode currentNode, DraftNode targetNode) {
        if (currentNode instanceof DraftTreeItem treeItem) {
            if (treeItem.getDraftNode().equals(targetNode)) {
                return treeItem;
            }
        }
        for (int i = 0; i < currentNode.getChildCount(); i++) {
            TreeNode childNode = currentNode.getChildAt(i);
            DraftTreeItem result = findTreeItemRecursive(childNode, targetNode);
            if (result != null) {
                return result;
            }
        }
        return null;
    }
}
