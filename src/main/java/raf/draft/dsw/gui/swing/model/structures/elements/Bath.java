package raf.draft.dsw.gui.swing.model.structures.elements;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;

public class Bath extends RoomElement{
    public Bath(DraftNode parent) {
        super(parent);
    }
    public Bath(Bath source) {
        super(source);
    }

    @Override
    public ElementPrototype clone() {
        return new Bath(this);
    }
}
