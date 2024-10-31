package raf.draft.dsw.model.nodes;

import java.util.ArrayList;
import java.util.List;

public class DraftNodeComposite extends DraftNode {
    private List<DraftNode> children = new ArrayList<>();

    public DraftNodeComposite(String name) {
        super(name);
    }

    @Override
    public void addChild(DraftNode node) {
        children.add(node);
        node.setParent(this);
    }

    @Override
    public void removeChild(DraftNode node) {
        children.remove(node);
        node.setParent(null);
    }

    @Override
    public List<DraftNode> getChildren() {
        return children;
    }
}
