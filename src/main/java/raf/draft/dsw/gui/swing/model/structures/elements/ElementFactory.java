package raf.draft.dsw.gui.swing.model.structures.elements;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.structures.RoomElement;

public class ElementFactory {
    public static RoomElement getRoomElement(String type, DraftNode parent) {
        return switch (type.toLowerCase()){
            case "bed" -> new Bed(parent);
            case "bath" -> new Bath(parent);
            case "boiler" -> new Boiler(parent);
            case "door" -> new Door(parent);
            case "sink" -> new Sink(parent);
            case "table" -> new Table(parent);
            case "toilet" -> new Toilet(parent);
            case "wardrobe" -> new Wardrobe(parent);
            case "washingmachine" -> new WashingMachine(parent);
            default -> throw new IllegalArgumentException("Unknown node type: "+type);
        };
    }
}
