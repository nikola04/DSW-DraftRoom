package raf.draft.dsw.gui.swing.view;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.controller.commands.concrete.RotateCommand;
import raf.draft.dsw.gui.swing.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.controller.states.StateManager;
import raf.draft.dsw.gui.swing.model.events.EventModel;
import raf.draft.dsw.gui.swing.model.events.EventType;
import raf.draft.dsw.gui.swing.model.structures.Building;
import raf.draft.dsw.gui.swing.model.structures.Project;
import raf.draft.dsw.gui.swing.model.structures.Room;

import javax.swing.*;
import java.awt.*;

public class ProjectView extends JPanel implements ISubscriber {
    private Project project;
    private Room room;
    private StateManager stateManager;
    private JLabel labelProject;
    private JLabel labelProjectAuthor;
    private JLabel labelBuilding;
    public ProjectView() {
        initialize();
        initializeGUI();
    }
    private void initialize() {
        updateProject(MainFrame.getInstance().getTabPaneModel().getProject());
        MainFrame.getInstance().getTabPaneModel().addSubscriber(this);
        this.stateManager = new StateManager();
    }
    private void initializeGUI(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentX(LEFT_ALIGNMENT);
        this.labelProject = new JLabel();
        this.labelProjectAuthor = new JLabel();
        labelBuilding = new JLabel();
        updateProjectLabel();
        updateProjectAuthorLabel();
        updateBuildingLabel();
        add(labelProject);
        add(labelProjectAuthor);
        add(labelBuilding);
    }
    public void onMouseClick(RoomView roomView, Point point) {
        this.stateManager.getCurrentState().handleMouseClick(roomView, point);
    }
    public void onMousePress(RoomView roomView, Point point) {
        this.stateManager.getCurrentState().handleMousePress(roomView, point);
    }
    public void onMouseDrag(RoomView roomView, Point point) {
        this.stateManager.getCurrentState().handleMouseDrag(roomView, point);
    }
    public void onMouseRelease(RoomView roomView, Point point) {
        this.stateManager.getCurrentState().handleMouseRelease(roomView, point);
    }
    public void onMouseWheel(RoomView roomView, double rotation) {
        this.stateManager.getCurrentState().handleMouseWheel(roomView, rotation);
    }
    public void startAddState(){
        if(room != null && room.isDimensionsSet())
            this.stateManager.setAddState();
    }
    public void startResizeState(){
        if(room != null && room.isDimensionsSet())
            this.stateManager.setResizeState();
    }
    public void startDeleteState(){
        if(room != null && room.isDimensionsSet()) {
            this.room.deleteSelectedElements();
            this.stateManager.setDeleteState();
        }
    }
    public void startSelectionState(){
        if(room != null && room.isDimensionsSet())
            this.stateManager.setSelectState();
    }
    public void startZoomState(){
        if(room != null && room.isDimensionsSet())
            this.stateManager.setZoomState();
    }
    public void startMoveState(){
        if(room != null && room.isDimensionsSet())
            this.stateManager.setMoveState();
    }
    public void startEditState() {
        if (room != null && room.isDimensionsSet())
            this.stateManager.setEditState();
    }
    public void pasteCopiedElements(){
        if(room != null && room.isDimensionsSet())
            room.cloneCopiedElements();
    }
    public void rotateSelectedElements(int rotation){
        if(room == null || !room.isDimensionsSet()) return;
        RotateCommand rotateCommand = new RotateCommand(room, rotation);
        ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(rotateCommand);
    }
    public void copySelectedElements() {
        if(room != null && room.isDimensionsSet())
            room.copySelectedElements();
    }
    private void updateProjectLabel(){
        String projectName = project != null ? project.getName() : "/";
        labelProject.setText("Project: " + projectName);
    }
    private void updateProjectAuthorLabel(){
        String projectAuthor = project != null ? project.getAuthor() : "/";
        labelProjectAuthor.setText("Author: " + projectAuthor);
    }
    private void updateBuildingLabel(){
        String buildingName = "/";
        if(this.room != null && this.room.getParent() instanceof Building building)
            buildingName = building.getName();
        labelBuilding.setText("Building: " + buildingName);
    }
    private void updateProject(Project newProject){
        if(this.project != null)
            this.project.removeSubscriber(this);
        this.project = newProject;
        if(this.project != null)
            this.project.addSubscriber(this);
    }
    private void setRoom(Room room){
        if(this.room != null) {
            if (this.room.getParent() instanceof Building building)
                building.removeSubscriber(this);
        }
        this.room = room;
        if(this.room != null){
            if(this.room.getParent() instanceof Building building)
                building.addSubscriber(this);
        }
        this.stateManager = new StateManager();
    }
    public void refresh(){
        updateProjectLabel();
        updateProjectAuthorLabel();
        updateBuildingLabel();
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
            else if(event.getType() == EventType.TAB_SELECTED){
                if(event.getValue() instanceof Room room) {
                    setRoom(room);
                }else if(event.getValue() == null)
                    setRoom(null);
                refresh();
            }
            else if(event.getType() == EventType.TAB_DELETE){
                if(event.getValue() instanceof RoomView deletedTab) {
                    if(deletedTab.getRoom() != null && deletedTab.getRoom() == this.room){
                        setRoom(null);
                        refresh();
                    }
                }
            }
            else if(event.getType() == EventType.BUILDING_NAME)
                updateBuildingLabel();
        }
    }
}
