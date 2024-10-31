package raf.draft.dsw.model.nodes;

import java.util.ArrayList;
import java.util.List;

public abstract class DraftNode {
    protected String name;
    protected DraftNode parent;

    public DraftNode(String name) {
        this.name = name;
    }

    public abstract void addChild(DraftNode node);
    public abstract void removeChild(DraftNode node);
    public abstract List<DraftNode> getChildren();

    public String getName() {
        return name;
    }

    public void setParent(DraftNode parent) {
        this.parent = parent;
    }

    public DraftNode getParent() {
        return parent;
    }
}
