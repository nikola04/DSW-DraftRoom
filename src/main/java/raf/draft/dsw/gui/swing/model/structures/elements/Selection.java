package raf.draft.dsw.gui.swing.model.structures.elements;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.structures.RoomElement;

public class Selection extends RoomElement {
    public Selection(DraftNode parent, String name, int x, int y, int width, int height) {
        super(parent, name, x, y, width, height);
        this.isRecognizable = false;
    }

    public Selection() {
    }

    @Override
    public ClonePrototype clone() {
        return null;
    }
}
