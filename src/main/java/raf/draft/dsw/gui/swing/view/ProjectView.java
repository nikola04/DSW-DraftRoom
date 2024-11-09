package raf.draft.dsw.gui.swing.view;

import raf.draft.dsw.gui.swing.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.model.events.EventModel;
import raf.draft.dsw.gui.swing.model.events.EventType;
import raf.draft.dsw.gui.swing.model.structures.Project;

import javax.swing.*;

public class ProjectView extends JPanel implements ISubscriber {
    private JLabel labelProject;
    public ProjectView() {
        initialize();
        initializeGUI();
    }
    private void initialize() {
        labelProject = new JLabel();
    }
    private void initializeGUI(){
        Project project = MainFrame.getInstance().getTabPaneModel().getProject();
        updateProjectLabel(project != null ? project.getName() : "/");
        add(labelProject);
    }
    private void updateProjectLabel(String projectName){
        labelProject.setText("Project: " + projectName);
    }
    @Override
    public void update(Object value) {
        if(value instanceof EventModel event)
            if(event.getType() == EventType.PROJECT_SET) {
                if (event.getValue() instanceof Project project)
                    updateProjectLabel(project.getName());
                else updateProjectLabel("/");
            }

    }
}
