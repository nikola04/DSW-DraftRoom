package raf.draft.dsw.gui.swing.tree.controller;

import raf.draft.dsw.gui.swing.model.structures.Building;
import raf.draft.dsw.gui.swing.model.structures.Project;
import raf.draft.dsw.gui.swing.model.structures.Room;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.view.MainFrame;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class DraftTreeSelectionListener implements TreeSelectionListener {

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        DraftTreeItem item = (DraftTreeItem) path.getLastPathComponent();
        Project selectedProject = null;
        if(item.getDraftNode() instanceof Project project)
            selectedProject = project;
        else if(item.getDraftNode() instanceof Building building && building.getParent() instanceof Project project){
            selectedProject = project;
        }
        else if(item.getDraftNode() instanceof Room room){
            if(room.getParent() instanceof Project project)
                selectedProject = project;
            if(room.getParent() instanceof Building building && building.getParent() instanceof Project project)
                selectedProject = project;
        }
        if(selectedProject != null && selectedProject != MainFrame.getInstance().getTabPane().getProject())
            MainFrame.getInstance().getTabPane().setProject(selectedProject);
        System.out.println("Selektovan cvor:"+ item.getDraftNode().getName());
        System.out.println("getPath: "+e.getPath());
    }
}
