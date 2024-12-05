package raf.draft.dsw.gui.swing.view.painters;

import raf.draft.dsw.gui.swing.model.structures.RoomElement;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class WashingMachinePainter extends ElementPainter {
    public WashingMachinePainter(RoomElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform originalTransform = g2d.getTransform();
        applyTransformations(g2d);

        g2d.drawRect(element.getX(), element.getY(), element.getWidth(), element.getHeight());

        int centerX = element.getX() + element.getWidth() / 2;
        int centerY = element.getY() + element.getHeight() / 2;
        int radius = Math.min(element.getWidth(), element.getHeight()) / 4;
        g2d.drawOval(centerX - radius, centerY - radius, radius * 2, radius * 2);

        applySelectionBorder(g2d);
        g2d.setTransform(originalTransform);
    }
}
