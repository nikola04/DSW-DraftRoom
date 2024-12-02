package raf.draft.dsw.gui.swing.model.structures.elements;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;

public class Boiler extends RoomElement {
    public Boiler(String name, DraftNode parent) {
        super(name, parent);
    }
    public Boiler(Boiler source){
        super(source);
    }

    @Override
    public ElementPrototype clone() {
        return new Boiler(this);
    }
}
