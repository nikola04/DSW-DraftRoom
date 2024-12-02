package raf.draft.dsw.gui.swing.model.structures.elements;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;

public class Door extends RoomElement {
    public Door(DraftNode parent) {
        super(parent);
    }
    public Door(Door source){
        super(source);
    }
    @Override
    public ElementPrototype clone() {
        return new Door(this);
    }
}
