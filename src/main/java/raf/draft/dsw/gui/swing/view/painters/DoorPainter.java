package raf.draft.dsw.gui.swing.view.painters;

import raf.draft.dsw.gui.swing.model.structures.RoomElement;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class DoorPainter extends ElementPainter {
    public DoorPainter(RoomElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform originalTransform = g2d.getTransform();
        applyTransformations(g2d);

        int centerX = element.getX();
        int centerY = element.getY();
        int width = element.getWidth();
        int height = element.getHeight();

        g2d.drawArc(centerX, centerY, width, height, 0, 180); // Polukrug

        applySelectionBorder(g2d);
        g2d.setTransform(originalTransform);
    }
}
