package raf.draft.dsw.gui.swing.view.painters;

import raf.draft.dsw.gui.swing.model.structures.RoomElement;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class BoilerPainter extends ElementPainter {
    public BoilerPainter(RoomElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform originalTransform = g2d.getTransform();
        applyTransformations(g2d);

        int centerX = element.getX() + element.getWidth() / 2;
        int centerY = element.getY() + element.getHeight() / 2;
        int radius = Math.min(element.getWidth(), element.getHeight()) / 2;
        int circleX = centerX - radius;
        int circleY = centerY - radius;

        int margin = (int) (radius * 0.5); // 20% margine unutar kruga

        g2d.drawOval(circleX, circleY, radius * 2, radius * 2);

        int innerRadius = radius - margin;

        int x1 = centerX - innerRadius;
        int y1 = centerY - innerRadius;
        int x2 = centerX + innerRadius;
        int y2 = centerY + innerRadius;

        g2d.drawLine(x1, y1, x2, y2);
        g2d.drawLine(x1, y2, x2, y1);

        applySelectionBorder(g2d);
        g2d.setTransform(originalTransform);
    }
}
