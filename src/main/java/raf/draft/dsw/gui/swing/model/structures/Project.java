package raf.draft.dsw.gui.swing.model.structures;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.nodes.DraftNodeComposite;

import java.util.ArrayList;

public class Project extends DraftNodeComposite {
    private String author;
    private String path;

    public Project(String name, String author, String path, DraftNode parent) {
        super(name, parent);
        this.author = author;
        this.path = path;
    }

    public Project(String name, DraftNode parent) {
        super(name, parent);
        this.author = "";
        this.path = "";
    }

    public ArrayList<Room> getAllRooms() {
        ArrayList<Room> rooms = new ArrayList<>();
        for (DraftNode node : getChildren()) {
            if(node instanceof Building building) {
                for (DraftNode buildingNode : building.getChildren())
                    if (buildingNode instanceof Room room)
                        rooms.add(room);
            }
            if(node instanceof Room room)
                rooms.add(room);
        }
        return rooms;
    }

    public String getAuthor() {
        return author;
    }

    public String getPath() {
        return path;
    }
}
