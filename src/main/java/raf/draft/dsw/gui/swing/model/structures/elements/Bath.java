package raf.draft.dsw.gui.swing.model.structures.elements;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.structures.RoomElement;

public class Bath extends RoomElement {
    public Bath(DraftNode parent) {
        super(parent);
    }
    public Bath(Bath source) {
        super(source);
    }

    @Override
    public RoomElement clone() {
        return new Bath(this);
    }
}
