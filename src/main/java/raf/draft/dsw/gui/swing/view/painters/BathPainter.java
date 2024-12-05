package raf.draft.dsw.gui.swing.view.painters;

import raf.draft.dsw.gui.swing.model.structures.elements.Bath;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class BathPainter extends ElementPainter {
    public BathPainter(Bath element) {
        super(element);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform originalTransform = g2d.getTransform();
        applyTransformations(g2d);
        g2d.drawRect(element.getX(), element.getY(), element.getWidth(), element.getHeight());
        int innerWidth = (int) (element.getWidth()*0.75);
        int innerHeight = (int) (element.getHeight()*0.75);
        int innerX = element.getX() + (element.getWidth() - innerWidth) / 2;
        int innerY = element.getY() + (element.getHeight() - innerHeight) / 2;
        g.drawOval(innerX, innerY, innerWidth, innerHeight);
        applySelectionBorder(g2d);
        g2d.setTransform(originalTransform);
    }
}
