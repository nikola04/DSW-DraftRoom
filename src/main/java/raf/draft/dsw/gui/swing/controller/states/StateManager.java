package raf.draft.dsw.gui.swing.controller.states;

import raf.draft.dsw.gui.swing.controller.states.concrete.*;

public class StateManager {
    private State currentState;
    private AddState addState;
    private SelectState selectState;
    private ZoomState zoomState;
    private DeleteState deleteState;
    private ResizeState resizeState;
    private MoveState moveState;
    public StateManager() {
        initialize();
    }
    public void initialize() {
        this.addState = new AddState();
        this.zoomState = new ZoomState();
        this.selectState = new SelectState();
        this.deleteState = new DeleteState();
        this.resizeState = new ResizeState();
        this.moveState = new MoveState();
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
    public void setResizeState() {
        currentState = resizeState;
    }
    public void setMoveState() {
        currentState = moveState;
    }
}
