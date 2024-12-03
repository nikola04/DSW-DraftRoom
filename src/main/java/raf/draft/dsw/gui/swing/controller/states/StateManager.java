package raf.draft.dsw.gui.swing.controller.states;

import raf.draft.dsw.gui.swing.controller.states.concrete.EditRoomState;

public class StateManager {
    private State currentState;
    private EditRoomState editRoomState;
    public StateManager() {
        initialize();
    }
    public void initialize() {
        this.editRoomState = new EditRoomState();
        setEditRoomState();
    }
    public State getCurrentState() {
        return currentState;
    }
    public void setEditRoomState() {
        currentState = editRoomState;
    }
}
