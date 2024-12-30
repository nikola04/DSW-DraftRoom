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
        Project selectedProject = item.getDraftNode().findParentProject();
        if(selectedProject != null && selectedProject != MainFrame.getInstance().getTabPaneModel().getProject())
            MainFrame.getInstance().getTabPaneModel().setProject(selectedProject);
    }
}
