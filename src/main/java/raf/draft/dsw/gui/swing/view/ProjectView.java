package raf.draft.dsw.gui.swing.view;

import raf.draft.dsw.gui.swing.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.model.events.EventModel;
import raf.draft.dsw.gui.swing.model.events.EventType;
import raf.draft.dsw.gui.swing.model.structures.Project;

import javax.swing.*;

public class ProjectView extends JPanel implements ISubscriber {
    private Project project;
    private JLabel labelProject;
    private JLabel labelProjectAuthor;
    public ProjectView() {
        initialize();
        initializeGUI();
    }
    private void initialize() {
        updateProject(MainFrame.getInstance().getTabPaneModel().getProject());
        MainFrame.getInstance().getTabPaneModel().addSubscriber(this);
    }
    private void initializeGUI(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentX(LEFT_ALIGNMENT);
        this.labelProject = new JLabel();
        this.labelProjectAuthor = new JLabel();
        updateProjectLabel();
        updateProjectAuthorLabel();
        add(labelProject);
        add(labelProjectAuthor);
    }
    private void updateProjectLabel(){
        String projectName = project != null ? project.getName() : "/";
        labelProject.setText("Project: " + projectName);
    }

    private void updateProjectAuthorLabel(){
        String projectAuthor = project != null ? project.getAuthor() : "/";
        labelProjectAuthor.setText("Author: " + projectAuthor);
    }
    private void updateProject(Project newProject){
        if(this.project != null)
            this.project.removeSubscriber(this);
        this.project = newProject;
        if(this.project != null)
            this.project.addSubscriber(this);
    }
    public void refresh(){
        updateProjectLabel();
        updateProjectAuthorLabel();
    }
    @Override
    public void update(Object value) {
        if(value instanceof EventModel event) {
            if (event.getType() == EventType.PROJECT_SET) {
                if (event.getValue() instanceof Project newProject)
                    updateProject(newProject);
                else if(event.getValue() == null)
                    updateProject(null);
                refresh();
            }
            else if(event.getType() == EventType.PROJECT_NAME)
                updateProjectLabel();
            else if (event.getType() == EventType.PROJECT_AUTHOR)
                updateProjectAuthorLabel();
        }
    }
}
