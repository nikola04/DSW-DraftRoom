package raf.draft.dsw.gui.swing.view.painters;

import raf.draft.dsw.gui.swing.model.structures.RoomElement;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class BedPainter extends ElementPainter {
    public BedPainter(RoomElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform originalTransform = g2d.getTransform();
        applyTransformations(g2d);
        g2d.drawRect(element.getX(), element.getY(), element.getWidth(), element.getHeight());
        int pillowWidth = element.getWidth() / 3;
        int pillowHeight = element.getHeight() / 4;
        int pillowX = element.getX() + (element.getWidth() - pillowWidth) / 2;
        int pillowY = element.getY();
        g.drawRect(pillowX, pillowY, pillowWidth, pillowHeight);
        applySelectionBorder(g2d);
        g2d.setTransform(originalTransform);
    }
}
