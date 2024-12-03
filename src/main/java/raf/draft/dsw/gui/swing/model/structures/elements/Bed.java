package raf.draft.dsw.gui.swing.model.structures.elements;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;

public class Bed extends RoomElement {
    public Bed(DraftNode parent) {
        super(parent);
    }
    public Bed(Bed source){
        super(source);
    }

    @Override
    public ElementPrototype clone() {
        return new Bed(this);
    }
}
