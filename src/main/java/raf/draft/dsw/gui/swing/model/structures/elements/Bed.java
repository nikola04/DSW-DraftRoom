package raf.draft.dsw.gui.swing.model.structures.elements;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.structures.RoomElement;

public class Bed extends RoomElement {
    public Bed(DraftNode parent) {
        super(parent);
    }
    public Bed(){
        super();
    }
    public Bed(Bed source){
        super(source);
    }

    @Override
    public ClonePrototype clone() {
        return new Bed(this);
    }
}
