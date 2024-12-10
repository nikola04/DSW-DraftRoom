package raf.draft.dsw.gui.swing.model.structures.elements;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.structures.RoomElement;

public class Door extends RoomElement {
    public Door(DraftNode parent) {
        super(parent);
    }
    public Door(Door source){
        super(source);
    }
    @Override
    public ClonePrototype clone() {
        return new Door(this);
    }
}
