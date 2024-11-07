package raf.draft.dsw.model.structures;

import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

public class Building extends DraftNodeComposite {
    private String path;
    public Building(String name, DraftNode parent) {
        super(name, parent);
        this.path = "";
    }
    public Building(String name, String path, DraftNode parent) {
        super(name, parent);
        this.path = path;
    }
}
