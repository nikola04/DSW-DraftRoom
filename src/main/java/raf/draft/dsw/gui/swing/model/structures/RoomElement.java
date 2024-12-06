package raf.draft.dsw.gui.swing.model.structures;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.nodes.DraftNodeLeaf;
import raf.draft.dsw.gui.swing.model.structures.elements.ElementPrototype;

public abstract class RoomElement extends DraftNodeLeaf implements ElementPrototype {
    private static int counter = 0;
    protected int x, y;
    protected int width, height;
    protected int rotateRatio = 0; // od 0 do 3
    protected boolean isRecognizable = true;
    private boolean selected = false;
    public RoomElement(DraftNode parent) {
        super("Element " + RoomElement.getCounter(), parent);
    }
    public RoomElement(RoomElement source) {
        super(source.getName(), source.getParent());
        initialize(source.getX(), source.getY(), source.getWidth(), source.getHeight());
        this.isRecognizable = source.isRecognizable();
        this.rotateRatio = source.getRotateRatio();
        this.selected = false;
    }
    public RoomElement(DraftNode parent, String name, int x, int y, int width, int height) {
        super(name, parent);
        initialize(x, y, width, height);
    }
    public void initialize(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public static int getCounter(){
        return counter++;
    }
    public abstract RoomElement clone();

    public boolean overlaps(int x, int y, int width, int height) {
        if(!isRecognizable) return false;
        int thisRight = this.x + this.width;
        int thisBottom = this.y + this.height;
        int otherRight = x + width;
        int otherBottom = y + height;
        return this.x < otherRight && thisRight > x &&
                this.y < otherBottom && thisBottom > y;
    }

    @Override
    public Room getParent() {
        return (Room) super.getParent();
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isRecognizable() {
        return isRecognizable;
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
