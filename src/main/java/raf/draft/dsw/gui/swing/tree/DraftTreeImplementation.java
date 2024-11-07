package raf.draft.dsw.gui.swing.tree;

import raf.draft.dsw.gui.swing.controller.messagegenerator.ConsoleLogger;
import raf.draft.dsw.gui.swing.controller.messagegenerator.MessageGenerator;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.tree.view.DraftTreeView;
import raf.draft.dsw.model.messages.Message;
import raf.draft.dsw.model.messages.MessageType;
import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.structures.Building;
import raf.draft.dsw.model.structures.Project;
import raf.draft.dsw.model.structures.ProjectExplorer;
import raf.draft.dsw.model.structures.Room;

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
    public DraftNode createNode(DraftNode parent){
        if(parent instanceof ProjectExplorer){
            String nodeName = JOptionPane.showInputDialog(null,"Enter project name:", "Enter Name", JOptionPane.PLAIN_MESSAGE);
            // todo: check if name is valid
            return new Project(nodeName, "", "", parent);
        }
        if(parent instanceof Project){
            String[] options = {"Building", "Room"};
            int selected = JOptionPane.showOptionDialog(null, "Do you want to add Building or Room into Project", "Select Building or Room", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, null);
            // todo: check if name is valid
            String nodeName = JOptionPane.showInputDialog(null, "Enter " + options[selected] + " name:", "Enter Name", JOptionPane.PLAIN_MESSAGE);
            if(selected == 0)
                return new Building(nodeName, parent);
            else return new Room(nodeName);
        }
        if(parent instanceof Building){
            String nodeName = JOptionPane.showInputDialog(null, "Enter Room name:", "Enter Name", JOptionPane.PLAIN_MESSAGE);
            // todo: check if name is valid
            return new Room(nodeName);
        }
        return null;
    }

    @Override
    public void addChild(DraftTreeItem parent) {
        if(parent == null || !(parent.getDraftNode() instanceof DraftNodeComposite))
            return;
        DraftNode child = createNode(parent.getDraftNode());
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
