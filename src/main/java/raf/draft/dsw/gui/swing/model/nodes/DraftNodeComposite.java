package raf.draft.dsw.gui.swing.model.nodes;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.view.MainFrame;

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
    public void setChildren(List<DraftNode> children) { // for jackson
        this.children = new ArrayList<>(children);
        for (DraftNode child : children){
            child.setParent(this);
        }
    }
}
