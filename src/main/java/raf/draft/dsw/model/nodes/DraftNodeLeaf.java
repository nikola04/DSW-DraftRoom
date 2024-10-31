package raf.draft.dsw.model.nodes;

import java.util.Collections;
import java.util.List;

public class DraftNodeLeaf extends DraftNode {
    public DraftNodeLeaf(String name) {
        super(name);
    }

    @Override
    public void addChild(DraftNode node) {
        throw new UnsupportedOperationException("Leaf node cannot have children");
    }

    @Override
    public void removeChild(DraftNode node) {
        throw new UnsupportedOperationException("Leaf node cannot have children");
    }

    @Override
    public List<DraftNode> getChildren() {
        return Collections.emptyList();
    }
}
