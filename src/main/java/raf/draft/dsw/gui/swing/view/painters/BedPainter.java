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
        int width = element.getWidth();
        int height = element.getHeight();
        g2d.drawRect(element.getX(), element.getY(), width, height);
        int pillowWidth = width / 3;
        int pillowHeight = height / 4;
        int pillowX = element.getX() + (width - pillowWidth) / 2;
        int pillowY = element.getY() + height / 10;
        g.drawRect(pillowX, pillowY, pillowWidth, pillowHeight);
        applySelectionBorder(g2d);
        g2d.setTransform(originalTransform);
    }
}
