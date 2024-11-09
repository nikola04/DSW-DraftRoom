package raf.draft.dsw.gui.swing.model.structures;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.nodes.DraftNodeLeaf;

import javax.swing.*;

public class Room extends DraftNodeLeaf {
    public Room(String name, DraftNode parent) {
        super(name, parent);
    }
}
