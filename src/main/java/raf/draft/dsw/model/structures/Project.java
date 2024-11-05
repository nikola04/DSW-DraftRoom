package raf.draft.dsw.model.structures;

import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;

public class Project extends DraftNodeComposite {
    private String author;
    private String path;

    public Project(String name, String author, String path, DraftNode parent) {
        super(name, parent);
        this.author = author;
        this.path = path;
    }

    public String getAuthor() {
        return author;
    }

    public String getPath() {
        return path;
    }
}
