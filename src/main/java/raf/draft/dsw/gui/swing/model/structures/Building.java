package raf.draft.dsw.gui.swing.model.structures;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.nodes.DraftNodeComposite;

import java.awt.*;
import java.util.Random;

public class Building extends DraftNodeComposite {
    private String path;
    private Color color;
    public Building(String name, DraftNode parent) {
        super(name, parent);
        this.path = "";
        initialize();
    }
    public Building(String name, String path, DraftNode parent) {
        super(name, parent);
        this.path = path;
        initialize();
    }
    private void initialize(){
        Random rnd = new Random();
        this.color = new Color(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255));
    }

    public Color getColor() {
        return color;
    }
}
