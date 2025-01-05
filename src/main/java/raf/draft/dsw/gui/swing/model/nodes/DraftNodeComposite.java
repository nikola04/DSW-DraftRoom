package raf.draft.dsw.gui.swing.model.nodes;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.model.messages.MessageType;
import raf.draft.dsw.gui.swing.model.structures.*;
import raf.draft.dsw.gui.swing.model.structures.elements.Selection;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.view.MainFrame;

import java.util.ArrayList;
import java.util.List;

public class DraftNodeComposite extends DraftNode {
    private List<DraftNode> children = new ArrayList<>();

    public DraftNodeComposite(String name) {
        super(name, null);
    }
    public DraftNodeComposite(String name, DraftNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(DraftNode node) {
        children.add(node);
        if(node instanceof Room room){
            Project activeProject = MainFrame.getInstance().getTabPaneModel().getProject();
            if(activeProject != null && this.findParentProject().equals(activeProject)){
                MainFrame.getInstance().getTabPaneModel().addTab(room);
            }
        } else if(node instanceof Building building) {
            for (DraftNode child : building.getChildren())
                if (child instanceof Room room)
                    MainFrame.getInstance().getTabPaneModel().addTab(room);
        } else if(node instanceof Project project) {
            Project activeProject = MainFrame.getInstance().getTabPaneModel().getProject();
            if (activeProject == null || project.equals(activeProject))
                MainFrame.getInstance().getTabPaneModel().setProject(project);
        }
        super.onAppliedChange();
    }

    @Override
    public void removeChild(DraftNode node) {
        if(node instanceof Room room)
            MainFrame.getInstance().getTabPaneModel().removeTabByRoom(room);
        else if(node instanceof Building building) {
            for (DraftNode child : building.getChildren())
                if (child instanceof Room room)
                    MainFrame.getInstance().getTabPaneModel().removeTabByRoom(room);
        }
        else if(node instanceof Project project) {
            if (project.equals(MainFrame.getInstance().getTabPaneModel().getProject()))
                MainFrame.getInstance().getTabPaneModel().setProject(null);
        }
        children.remove(node);
        super.onAppliedChange();
    }

    public List<DraftNode> getChildren() {
        return children;
    }
    public void setChildren(List<DraftNode> children) { // for jackson
        this.children = new ArrayList<>();
        for (DraftNode child : children){
            if(child instanceof Selection) continue;
            this.children.add(child);
            child.setParent(this);
            if(child instanceof Room r) {
                r.initialize();
                r.setDimensionsSet(true);
            }
        }
    }
}
