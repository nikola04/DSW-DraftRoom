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
    protected void applySelectionBorder(Graphics2D g2d) {
        if (!element.isSelected()) return;
        g2d.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10, new float[]{5, 3}, 0));
        g2d.setColor(new Color(0, 122, 255));
        int x = element.getX() - 1;
        int y = element.getY() - 1;
        int width = element.getWidth() + 2;
        int height = element.getHeight() + 2;
        g2d.drawRect(x, y, width, height);
        g2d.setStroke(new BasicStroke());
        g2d.setColor(Color.BLACK);
    }
    protected void applyTransformations(Graphics2D g2d) {
        double rotateAngle = Math.toRadians(element.getRotateRatio() * 90);
        AffineTransform rotation = AffineTransform.getRotateInstance(rotateAngle, element.getX() + (double) element.getWidth() / 2, element.getY() + (double) element.getHeight() / 2);
        g2d.transform(rotation);
    }
}
