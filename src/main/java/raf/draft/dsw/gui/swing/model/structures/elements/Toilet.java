package raf.draft.dsw.gui.swing.model.structures.elements;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.structures.RoomElement;

public class Toilet extends RoomElement {
    public Toilet(DraftNode parent) {
        super(parent);
    }
    public Toilet(Toilet source){
        super(source);
    }

    @Override
    public RoomElement clone() {
        return new Toilet(this);
    }
}
