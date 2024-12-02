package raf.draft.dsw.gui.swing.model.structures.elements;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;

public class Wardrobe extends RoomElement {
    public Wardrobe(String name, DraftNode parent) {
        super(name, parent);
    }
    public Wardrobe(Wardrobe source) {
        super(source);
    }

    @Override
    public ElementPrototype clone() {
        return new Wardrobe(this);
    }
}
