package raf.draft.dsw.gui.swing.controller.states;

import raf.draft.dsw.gui.swing.controller.states.concrete.*;

public class StateManager {
    private State currentState;
    private AddState addState;
    private SelectState selectState;
    private ZoomState zoomState;
    private DeleteState deleteState;
    public StateManager() {
        initialize();
    }
    public void initialize() {
        this.addState = new AddState();
        this.zoomState = new ZoomState();
        this.selectState = new SelectState();
        this.deleteState = new DeleteState();
        currentState = new EditRoomState();
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
    public void setDeleteState() {
        currentState = deleteState;
    }
}
