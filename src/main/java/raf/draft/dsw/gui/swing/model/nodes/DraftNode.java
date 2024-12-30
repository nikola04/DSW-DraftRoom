package raf.draft.dsw.gui.swing.model.nodes;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class DraftNode {
    protected String name;
    @JsonIgnore
    protected DraftNode parent;

    public DraftNode(String name, DraftNode parent) {
        this.name = name;
        this.parent = parent;
    }

    public abstract void addChild(DraftNode child);
    public abstract void removeChild(DraftNode child);
    public String getName() {
        return name;
    }
    public DraftNode getParent() {
        return parent;
    }

    public void setName(String name) {
        this.name = name;
    }
}
