package raf.draft.dsw.gui.swing.model.nodes;

public class DraftNodeLeaf extends DraftNode {
    public DraftNodeLeaf(String name, DraftNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(DraftNode node) {
        throw new UnsupportedOperationException("Leaf node cannot have children");
    }

    @Override
    public void removeChild(DraftNode node) {
        throw new UnsupportedOperationException("Leaf node cannot have children");
    }
}
