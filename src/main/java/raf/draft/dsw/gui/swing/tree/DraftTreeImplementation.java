package raf.draft.dsw.gui.swing.tree;

import raf.draft.dsw.core.ApplicationFramework;
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
    public void addChild(DraftTreeItem parent) {
        if(parent == null) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Please select Explorer, Building or Room first", MessageType.WARNING);
            return;
        }
        if(!(parent.getDraftNode() instanceof DraftNodeComposite) || (parent.getDraftNode() instanceof Room)) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("You cannot add anything here. Please select Explorer, Project or Building", MessageType.WARNING);
            return;
        }
        DraftNode child = createNode(parent.getDraftNode());
        if(child == null) return;
        parent.add(new DraftTreeItem(child));
        parent.getDraftNode().addChild(child);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
        if(child instanceof Room room){ // check if room is inside active project to update panel
            Project activeProject = MainFrame.getInstance().getTabPaneModel().getProject();
            if((parent.getDraftNode() instanceof Project project && project.equals(activeProject)) || parent.getParentProject().equals(activeProject)){
                MainFrame.getInstance().getTabPaneModel().addTab(room);
            }
        }
    }

    @Override
    public void addChild(DraftTreeItem parent, RoomElement element) {
        if(!(parent.getDraftNode() instanceof Room)) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("You must select Room if you want to add element", MessageType.ERROR);
            return;
        }

        parent.add(new DraftTreeItem(element));
        parent.getDraftNode().addChild(element);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void renameNode(DraftTreeItem item){
        String defaultName = item.getDraftNode().getName();
        Object nodeNameObj = JOptionPane.showInputDialog(null, "Enter new name:", "Enter Name", JOptionPane.PLAIN_MESSAGE, null, null, defaultName);
        if(nodeNameObj == null)
            return; // no message on cancel
        String nodeName = nodeNameObj.toString();
        if(nodeName.isEmpty()){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Node Name cannot be empty", MessageType.ERROR);
            return;
        }
        item.setName(nodeName);
        if(item.getDraftNode() instanceof Room room)
            MainFrame.getInstance().getTabPaneModel().renameTabByRoom(room, nodeName);
        ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Name is changed successfully", MessageType.INFO);
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
        if(item.getDraftNode() instanceof Room room) {
            room.getParent().removeChild(room);
            MainFrame.getInstance().getTabPaneModel().removeTabByRoom(room);
        }
        else if(item.getDraftNode() instanceof Building building) {
            for (DraftNode node : building.getChildren())
                if (node instanceof Room room)
                    MainFrame.getInstance().getTabPaneModel().removeTabByRoom(room);
            building.getParent().removeChild(building);
        }
        else if(item.getDraftNode() instanceof Project project) {
            if (project.equals(MainFrame.getInstance().getTabPaneModel().getProject())) {
                MainFrame.getInstance().getTabPaneModel().setProject(null);
                project.getParent().removeChild(project);
            }
        }else item.getDraftNode().getParent().removeChild(item.getDraftNode());
        item.removeFromParent();
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
        ApplicationFramework.getInstance().getMessageGenerator().generateMessage("You have deleted item successfully", MessageType.INFO);
    }
    public void removeNodeSilently(DraftTreeItem item) {
        if(item == null || item.getDraftNode() == null) return;
        if(item.getDraftNode() instanceof ProjectExplorer) return;
        item.removeFromParent();
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
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
