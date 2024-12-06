package raf.draft.dsw.gui.swing.model.structures.elements;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.structures.RoomElement;

public class Boiler extends RoomElement {
    public Boiler(DraftNode parent) {
        super(parent);
    }
    public Boiler(Boiler source){
        super(source);
    }

    @Override
    public RoomElement clone() {
        return new Boiler(this);
    }
}
