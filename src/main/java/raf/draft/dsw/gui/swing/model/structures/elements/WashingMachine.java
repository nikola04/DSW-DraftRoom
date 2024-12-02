package raf.draft.dsw.gui.swing.model.structures.elements;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;

public class WashingMachine extends RoomElement{
    public WashingMachine(DraftNode parent) {
        super(parent);
    }
    public WashingMachine(WashingMachine source) {
        super(source);
    }

    @Override
    public ElementPrototype clone() {
        return new WashingMachine(this);
    }

}
