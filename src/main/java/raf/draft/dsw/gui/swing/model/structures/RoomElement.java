package raf.draft.dsw.gui.swing.model.structures;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.nodes.DraftNodeLeaf;
import raf.draft.dsw.gui.swing.model.structures.elements.ElementPrototype;

public abstract class RoomElement extends DraftNodeLeaf implements ElementPrototype {
    private static int counter = 0;
    protected int x, y;
    protected int width, height;
    protected int rotateRatio; // od 0 do 3
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

    @Override
    public Room getParent() {
        return (Room) super.getParent();
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setRotateRatio(int rotateRatio) {
        this.rotateRatio = rotateRatio;
    }

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
