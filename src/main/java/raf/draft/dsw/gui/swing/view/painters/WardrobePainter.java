package raf.draft.dsw.gui.swing.view.painters;

import raf.draft.dsw.gui.swing.model.structures.RoomElement;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class WardrobePainter extends ElementPainter {
    public WardrobePainter(RoomElement element) {
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
        g2d.drawRect(x, y, width, height);

        int centerX = x + width / 2;
        g2d.drawLine(centerX, y, centerX, y + height);

        int handleOffset = 10;
        int handleCenterY = y + height / 2;
        g2d.fillOval(centerX - handleOffset - 2, handleCenterY - 2, 4, 4);
        g2d.fillOval(centerX + handleOffset - 2, handleCenterY - 2, 4, 4);

        applySelectionBorder(g2d);
        g2d.setTransform(originalTransform);
    }
}
