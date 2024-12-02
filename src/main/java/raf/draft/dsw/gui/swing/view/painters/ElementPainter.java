package raf.draft.dsw.gui.swing.view.painters;

import raf.draft.dsw.gui.swing.model.structures.elements.RoomElement;

import java.awt.*;
import java.awt.geom.AffineTransform;

public abstract class ElementPainter {
    protected RoomElement element;
    public ElementPainter(RoomElement element) {
        this.element = element;
    }
    public abstract void paint(Graphics g);
    protected void applyTransformations(Graphics2D g2d) {
        AffineTransform transform = new AffineTransform();
        transform.translate(element.getX(), element.getY());
        transform.scale(1, 1);
        double centerX = element.getWidth() / 2.0;
        double centerY = element.getHeight() / 2.0;
        transform.rotate(Math.toRadians(element.getRotateRatio()), centerX, centerY);

        g2d.transform(transform);
    }
}
