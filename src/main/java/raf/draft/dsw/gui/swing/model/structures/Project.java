package raf.draft.dsw.gui.swing.model.structures;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.nodes.DraftNodeComposite;

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

    public String getAuthor() {
        return author;
    }

    public String getPath() {
        return path;
    }
}
