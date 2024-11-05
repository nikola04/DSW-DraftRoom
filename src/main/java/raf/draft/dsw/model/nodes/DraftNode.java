package raf.draft.dsw.model.nodes;

import java.util.List;

public abstract class DraftNode {
    protected String name;
    protected DraftNode parent;

    public DraftNode(String name) {
        this.name = name;
    }

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
