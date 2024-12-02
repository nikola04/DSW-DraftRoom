package raf.draft.dsw.gui.swing.model.structures.elements;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.nodes.DraftNodeLeaf;

public abstract class RoomElement extends DraftNodeLeaf implements ElementPrototype {
    private static int counter = 0;
    private int x, y;
    private int width, height;
    private int rotateRatio; // od 0 do 3
    public RoomElement(DraftNode parent) {
        super("Element " + RoomElement.getCounter(), parent);
    }
    public RoomElement(RoomElement source) {
        super(source.getName(), source.getParent());
        this.x = source.getX();
        this.y = source.getY();
        this.width = source.getWidth();
        this.height = source.getHeight();
        this.rotateRatio = source.getRotateRatio();
    }
    public static int getCounter(){
        return counter++;
    }
    public abstract ElementPrototype clone();

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getRotateRatio() {
        return rotateRatio;
    }
}
