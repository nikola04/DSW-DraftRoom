package raf.draft.dsw.gui.swing.model.structures;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.nodes.DraftNodeComposite;
import raf.draft.dsw.gui.swing.model.nodes.DraftNodeLeaf;

public class Room extends DraftNodeComposite {
    private int dimensionXCm;
    private int dimensionYCm;
    public Room(String name, DraftNode parent) {
        super(name, parent);
        this.dimensionXCm = 0;
        this.dimensionYCm = 0;
    }
    public void setDimensions(int dimensionX, int dimensionY) {
        this.dimensionXCm = dimensionX;
        this.dimensionYCm = dimensionY;
    }
}