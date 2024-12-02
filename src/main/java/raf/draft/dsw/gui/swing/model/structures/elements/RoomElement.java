package raf.draft.dsw.gui.swing.model.structures.elements;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.nodes.DraftNodeLeaf;

public abstract class RoomElement extends DraftNodeLeaf implements ElementPrototype {
    private static int counter = 0;
    private int locationX, locationY;
    private int dimensionX, dimensionY;
    private int rotateRation; // od 0 do 3
    public RoomElement(String name, DraftNode parent) {
        super(name, parent);
    }
    public RoomElement(RoomElement source) {
        super(source.getName(), source.getParent());
        this.locationX = source.getLocationX();
        this.locationY = source.getLocationY();
        this.dimensionX = source.getDimensionX();
        this.dimensionY = source.getDimensionY();
        this.rotateRation = source.getRotateRation();
    }
    public static int getCounter(){
        return counter++;
    }
    public abstract ElementPrototype clone();

    public int getLocationX() {
        return locationX;
    }

    public int getLocationY() {
        return locationY;
    }

    public int getDimensionX() {
        return dimensionX;
    }

    public int getDimensionY() {
        return dimensionY;
    }

    public int getRotateRation() {
        return rotateRation;
    }
}
