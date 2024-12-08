package raf.draft.dsw.gui.swing.model.structures;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.nodes.DraftNodeLeaf;
import raf.draft.dsw.gui.swing.model.structures.elements.ElementPrototype;

import java.awt.*;

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
        super("Element" + RoomElement.getCounter(), source.getParent());
        initialize(source.getLogicalX(), source.getLogicalY(), source.getLogicalWidth(), source.getLogicalHeight());
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

    public boolean overlaps(Rectangle rect) {
        if (!isRecognizable) return false;
        Rectangle rotatedBounds = getRotatedBounds();

        int thisRight = rotatedBounds.x + rotatedBounds.width;
        int thisBottom = rotatedBounds.y + rotatedBounds.height;
        int otherRight = rect.x + rect.width;
        int otherBottom = rect.y + rect.height;
        return rotatedBounds.x < otherRight && thisRight > rect.x &&
                rotatedBounds.y < otherBottom && thisBottom > rect.y;
    }

    public Rectangle getRotatedBounds() {
        if(Math.abs(rotateRatio) == 1 || Math.abs(rotateRatio) == 3)
            return new Rectangle(x + width / 2 - height / 2, y + height / 2 - width / 2, height, width);
        return new Rectangle(x, y, width, height); // 180deg or 360deg
    }

    @Override
    public Room getParent() {
        return (Room) super.getParent();
    }

    public int getX() {
        return (int)(this.x * getParent().getPxConversionRatio());
    }

    public int getY() {
        return (int)(this.y * getParent().getPxConversionRatio());
    }

    public int getWidth() {
        return (int)(this.width * getParent().getPxConversionRatio());
    }

    public int getHeight() {
        return (int)(this.height * getParent().getPxConversionRatio());
    }
    public int getLogicalX(){
        return this.x;
    }
    public int getLogicalY(){
        return this.y;
    }
    public int getLogicalWidth(){
        return this.width;
    }
    public int getLogicalHeight(){
        return this.height;
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
        this.rotateRatio = rotateRatio % 4;
    }

    public int getRotateRatio() {
        return rotateRatio;
    }
}
