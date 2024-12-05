package raf.draft.dsw.gui.swing.controller.states;

import raf.draft.dsw.gui.swing.controller.states.concrete.AddState;
import raf.draft.dsw.gui.swing.controller.states.concrete.EditRoomState;
import raf.draft.dsw.gui.swing.controller.states.concrete.SelectState;
import raf.draft.dsw.gui.swing.controller.states.concrete.ZoomState;

public class StateManager {
    private State currentState;
    private EditRoomState editRoomState;
    private AddState addState;
    private SelectState selectState;
    private ZoomState zoomState;
    public StateManager() {
        initialize();
    }
    public void initialize() {
        this.editRoomState = new EditRoomState();
        this.addState = new AddState();
        this.zoomState = new ZoomState();
        this.selectState = new SelectState();
        currentState = editRoomState;
    }
    public State getCurrentState() {
        return currentState;
    }
    public void setSelectState(){
        currentState = selectState;
    }
    public void setZoomState() {
        currentState = zoomState;
    }
    public void setAddState() {
        currentState = addState;
    }
}
