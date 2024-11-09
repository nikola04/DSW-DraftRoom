package raf.draft.dsw.gui.swing.view;

import raf.draft.dsw.gui.swing.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.model.events.EventModel;
import raf.draft.dsw.gui.swing.model.events.EventType;
import raf.draft.dsw.gui.swing.model.structures.Building;
import raf.draft.dsw.gui.swing.model.structures.Room;

import javax.swing.*;
import java.awt.*;

public class RoomView extends JPanel implements ISubscriber {
    private Room room;
    private JLabel labelBuilding;
    public RoomView() {
        initialize();
        initializeGUI();
    }
    private void initialize(){
        MainFrame.getInstance().getTabPaneModel().addSubscriber(this);
    }
    private void initializeGUI(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentX(LEFT_ALIGNMENT);
        labelBuilding = new JLabel();
        updateBuildingLabel();
        add(labelBuilding);
    }
    private void updateBuildingLabel(){
        String buildingName = "/";
        if(this.room != null && this.room.getParent() instanceof Building building)
            buildingName = building.getName();
        labelBuilding.setText("Building: " + buildingName);
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
    }
    public void refresh(){
        updateBuildingLabel();
    }
    @Override
    public void update(Object value) {
        if(value instanceof EventModel event){
            if(event.getType() == EventType.TAB_SELECTED){
                if(event.getValue() instanceof Tab tab) {
                    setRoom(tab.getRoom());
                }else if(event.getValue() == null)
                    setRoom(null);
                refresh();
            }
            if(event.getType() == EventType.TAB_DELETE){
                if(event.getValue() instanceof Tab deletedTab) {
                    if(deletedTab.getRoom() != null && deletedTab.getRoom() == this.room){
                        this.room = null;
                        refresh();
                    }
                }
            }
            if(event.getType() == EventType.BUILDING_NAME)
                updateBuildingLabel();
        }
    }
}
