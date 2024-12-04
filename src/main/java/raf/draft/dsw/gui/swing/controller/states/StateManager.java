package raf.draft.dsw.gui.swing.controller.states;

import raf.draft.dsw.gui.swing.controller.states.concrete.AddState;
import raf.draft.dsw.gui.swing.controller.states.concrete.EditRoomState;

public class StateManager {
    private State currentState;
    private EditRoomState editRoomState;
    private AddState addState;
    public StateManager() {
        initialize();
    }
    public void initialize() {
        this.editRoomState = new EditRoomState();
        this.addState = new AddState();
        currentState = editRoomState;
    }
    public State getCurrentState() {
        return currentState;
    }
    public void setAddState() {
        currentState = addState;
    }
}
