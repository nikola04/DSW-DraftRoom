package raf.draft.dsw.gui.swing.model.structures.elements;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;

public class Sink extends RoomElement {
    public Sink(DraftNode parent) {
        super(parent);
    }
    public Sink(Sink source){
        super(source);
    }

    @Override
    public ElementPrototype clone() {
        return new Sink(this);
    }
}
