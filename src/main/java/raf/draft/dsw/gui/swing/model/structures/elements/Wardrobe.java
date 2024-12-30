package raf.draft.dsw.gui.swing.model.structures.elements;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.structures.RoomElement;

public class Wardrobe extends RoomElement {
    public Wardrobe(DraftNode parent) {
        super(parent);
    }
    public Wardrobe(Wardrobe source) {
        super(source);
    }

    public Wardrobe() {
    }

    @Override
    public ClonePrototype clone() {
        return new Wardrobe(this);
    }
}
