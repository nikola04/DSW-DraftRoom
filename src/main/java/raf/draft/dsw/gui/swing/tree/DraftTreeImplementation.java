package raf.draft.dsw.gui.swing.tree;

import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.tree.view.DraftTreeView;
import raf.draft.dsw.gui.swing.view.MainFrame;
import raf.draft.dsw.gui.swing.model.messages.MessageType;
import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.nodes.DraftNodeComposite;
import raf.draft.dsw.gui.swing.model.structures.Building;
import raf.draft.dsw.gui.swing.model.structures.Project;
import raf.draft.dsw.gui.swing.model.structures.ProjectExplorer;
import raf.draft.dsw.gui.swing.model.structures.Room;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

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
        if(parent instanceof ProjectExplorer){
            String nodeName = JOptionPane.showInputDialog(null,"Enter project name:", "Enter Name", JOptionPane.PLAIN_MESSAGE);
            if(nodeName.length() < 2){
                MainFrame.getInstance().getMessageGenerator().generateMessage("Name is too short. Try with more that 2 chars", MessageType.ERROR);
                return null;
                }
            return new Project(nodeName, "", "", parent);
        }
        if(parent instanceof Project){
            String[] options = {"Building", "Room"};
            int selected = JOptionPane.showOptionDialog(null, "Do you want to add Building or Room into Project", "Select Building or Room", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
            String nodeName = JOptionPane.showInputDialog(null, "Enter " + options[selected] + " name:", "Enter Name", JOptionPane.PLAIN_MESSAGE);
            if(nodeName.length() < 2) {
                MainFrame.getInstance().getMessageGenerator().generateMessage("Name is too short. Try with more that 2 chars", MessageType.ERROR);
                return null;
            }
            if(selected == 0)
                return new Building(nodeName, parent);
            else return new Room(nodeName);
        }
        if(parent instanceof Building){
            String nodeName = JOptionPane.showInputDialog(null, "Enter Room name:", "Enter Name", JOptionPane.PLAIN_MESSAGE);
            if(nodeName.length() < 2) {
                MainFrame.getInstance().getMessageGenerator().generateMessage("Name is too short. Try with more that 2 chars", MessageType.ERROR);
                return null;
            }
            return new Room(nodeName);
        }
        return null;
    }

    @Override
    public void addChild(DraftTreeItem parent) {
        if(parent == null) {
            MainFrame.getInstance().getMessageGenerator().generateMessage("Please select Explorer, Building or Room first", MessageType.WARNING);
            return;
        }
        if(!(parent.getDraftNode() instanceof DraftNodeComposite)){
            MainFrame.getInstance().getMessageGenerator().generateMessage("You cannot add anything here. Please select Explorer, Project or Building", MessageType.WARNING);
            return;
        }
        DraftNode child = createNode(parent.getDraftNode());
        if(child == null)
            return;
        parent.add(new DraftTreeItem(child));
        parent.getDraftNode().addChild(child);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public void renameNode(DraftTreeItem item){
        String defaultName = item.getDraftNode().getName();
        String nodeName = JOptionPane.showInputDialog(null, "Enter new name:", "Enter Name", JOptionPane.PLAIN_MESSAGE, null, null, defaultName).toString();
        if(nodeName.length() < 2){
            MainFrame.getInstance().getMessageGenerator().generateMessage("Name is too short. Try with more that 2 chars", MessageType.WARNING);
            return;
        }
        item.setName(nodeName);
        MainFrame.getInstance().getMessageGenerator().generateMessage("Name is changed successfully", MessageType.INFO);
    }

    @Override
    public void removeNode(DraftTreeItem item) {
        if(item == null || item.getDraftNode() == null) {
            MainFrame.getInstance().getMessageGenerator().generateMessage("Please select project, building or room first", MessageType.WARNING);
            return;
        }
        if(item.getDraftNode() instanceof ProjectExplorer) {
            MainFrame.getInstance().getMessageGenerator().generateMessage("You cannot delete project explorer", MessageType.WARNING);
            return;
        }
        item.removeFromParent();
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
        MainFrame.getInstance().getMessageGenerator().generateMessage("You have deleted item successfully", MessageType.INFO);
    }
}
