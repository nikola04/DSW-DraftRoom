package raf.draft.dsw.gui.swing.view.painters;

import raf.draft.dsw.gui.swing.model.structures.elements.Bath;

import java.awt.*;

public class BathPainter extends ElementPainter {
    public BathPainter(Bath element) {
        super(element);
    }

    @Override
    public void paint(Graphics g) {
        Bath bath = (Bath) this.element;
    }
}
