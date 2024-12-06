package raf.draft.dsw.gui.swing.model.structures.elements;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.structures.RoomElement;

public class Sink extends RoomElement {
    public Sink(DraftNode parent) {
        super(parent);
    }
    public Sink(Sink source){
        super(source);
    }

    @Override
    public RoomElement clone() {
        return new Sink(this);
    }
}
