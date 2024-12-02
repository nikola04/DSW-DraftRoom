package raf.draft.dsw.gui.swing.model.structures.elements;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;

public class Toilet extends RoomElement {
    public Toilet(String name, DraftNode parent) {
        super(name, parent);
    }
    public Toilet(Toilet source){
        super(source);
    }

    @Override
    public ElementPrototype clone() {
        return new Toilet(this);
    }
}
