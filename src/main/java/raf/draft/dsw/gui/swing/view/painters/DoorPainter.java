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

        int x = element.getX();
        int y = element.getY();
        int width = element.getWidth();
        int height = element.getHeight();

        int arcDiameter = Math.min(width, height) * 2;
        g2d.drawArc(x - (arcDiameter / 2) / 2, y - (arcDiameter / 2) / 2, arcDiameter, arcDiameter, 90, 90);

        int xStart = x + width / 2;
        int yStart = y - (arcDiameter / 2) / 2;
        int xEnd = xStart;
        int yEnd = y + height / 2;

        g2d.drawLine(xStart, yStart, xEnd, yEnd);

        applySelectionBorder(g2d);
        g2d.setTransform(originalTransform);
    }
}
