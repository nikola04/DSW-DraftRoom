package raf.draft.dsw.gui.swing.view.painters;

import raf.draft.dsw.gui.swing.model.structures.RoomElement;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class SinkPainter extends ElementPainter {
    public SinkPainter(RoomElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform originalTransform = g2d.getTransform();
        applyTransformations(g2d);

        int x = element.getX();
        int y = element.getY();
        int width = element.getWidth();
        int height = element.getHeight();
        int[] xPoints = { x + width / 2, x, x + width };
        int[] yPoints = { y + height, y, y };
        g2d.drawPolygon(xPoints, yPoints, 3);

        int centerX = x + width / 2;
        int centerY = y + (height / 3);
        g2d.fillOval(centerX - 2, centerY - 2, 4, 4);

        applySelectionBorder(g2d);
        g2d.setTransform(originalTransform);
    }
}
