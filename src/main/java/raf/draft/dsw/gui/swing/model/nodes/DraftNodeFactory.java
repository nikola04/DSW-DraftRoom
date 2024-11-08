package raf.draft.dsw.gui.swing.model.nodes;

import raf.draft.dsw.gui.swing.model.structures.Building;
import raf.draft.dsw.gui.swing.model.structures.Project;
import raf.draft.dsw.gui.swing.model.structures.Room;

public class DraftNodeFactory {
    public static DraftNode getDraftNode(String type, String name, DraftNode parent) {
        return switch (type.toLowerCase()){
            case "project" -> new Project(name, parent);
            case "building" -> new Building(name, parent);
            case "room" -> new Room(name, parent);
            default -> throw new IllegalArgumentException("Unknown node type: "+type);
        };
    }
}
