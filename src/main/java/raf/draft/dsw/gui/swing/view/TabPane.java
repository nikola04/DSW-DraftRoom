package raf.draft.dsw.gui.swing.view;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.structures.Building;
import raf.draft.dsw.gui.swing.model.structures.Project;
import raf.draft.dsw.gui.swing.model.structures.Room;

import javax.swing.*;

public class TabPane extends JTabbedPane {
    private Project project;
    public TabPane() {
        initialize();
    }
    private void initialize(){
    }
    public void setProject(Project project) {
        this.project = project;
        removeAll(); // reset all panes
        for(DraftNode child : project.getChildren()){
            if(child instanceof Room roomNode)
                addTab(new Tab(roomNode)); // all rooms under project
            else if(child instanceof Building buildingNode){
                for(DraftNode buildingChild : buildingNode.getChildren()) // all building rooms
                    if(buildingChild instanceof Room buildingRoom)
                        addTab(new Tab(buildingRoom));
            }
        }
    }

    private void addTab(Tab tab) {
        add(tab);
    }

    public Project getProject() {
        return project;
    }
}
