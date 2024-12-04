package raf.draft.dsw.gui.swing.view.painters;

import raf.draft.dsw.gui.swing.model.structures.Room;
import raf.draft.dsw.gui.swing.model.structures.RoomElement;

import java.awt.*;
import java.awt.geom.AffineTransform;

public abstract class ElementPainter {
    protected RoomElement element;
    public ElementPainter(RoomElement element) {
        this.element = element;
    }
    public abstract void paint(Graphics g);
    protected void applyTransformations(Graphics2D g2d) {
        double rotateAngle = Math.toRadians(element.getRotateRatio() * 90);
        AffineTransform rotation = AffineTransform.getRotateInstance(rotateAngle, element.getX() + (double) element.getWidth() / 2, element.getY() + (double) element.getHeight() / 2);
        g2d.transform(rotation);
    }
}
