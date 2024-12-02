package raf.draft.dsw.gui.swing.model.structures.elements;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;

public class Table extends RoomElement{
    public Table(String name, DraftNode parent) {
        super(name, parent);
    }
    public Table(Table source){
        super(source);
    }
    @Override
    public ElementPrototype clone() {
        return new Table(this);
    }
}
