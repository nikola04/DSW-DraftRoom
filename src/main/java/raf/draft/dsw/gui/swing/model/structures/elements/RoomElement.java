package raf.draft.dsw.gui.swing.model.structures.elements;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.nodes.DraftNodeLeaf;

public abstract class RoomElement extends DraftNodeLeaf {
    private static int counter = 0;
    public RoomElement(String name, DraftNode parent) {
        super(name, parent);
    }
    public static int getCounter(){
        return counter++;
    }
}
