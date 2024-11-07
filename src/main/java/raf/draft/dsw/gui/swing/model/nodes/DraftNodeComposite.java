package raf.draft.dsw.gui.swing.model.nodes;

import java.util.ArrayList;
import java.util.List;

public class DraftNodeComposite extends DraftNode {
    private List<DraftNode> children = new ArrayList<>();

    public DraftNodeComposite(String name) {
        super(name, null);
    }
    public DraftNodeComposite(String name, DraftNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(DraftNode node) {
        children.add(node);
    }

    @Override
    public void removeChild(DraftNode node) {
        children.remove(node);
    }

    public List<DraftNode> getChildren() {
        return children;
    }
}
