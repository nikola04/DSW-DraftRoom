package raf.draft.dsw.gui.swing.view.painters;

import raf.draft.dsw.gui.swing.model.structures.RoomElement;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class ToiletPainter extends ElementPainter {
    public ToiletPainter(RoomElement element) {
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

        // Gornji pravougaonik
        int rectHeight = height / 3;
        g2d.drawRect(x, y, width, rectHeight);

        // Donji luk (spoljašnji)
        int ovalWidth = width;
        int ovalHeight = (2 * height) / 3;
        int ovalX = x;
        int ovalY = y + rectHeight;
        g2d.drawArc(ovalX, ovalY, ovalWidth, ovalHeight, 0, -180);

        // Unutrašnji manji luk
        int innerMargin = 10;
        int innerOvalWidth = ovalWidth - 2 * innerMargin;
        int innerOvalHeight = ovalHeight - 2 * innerMargin;
        int innerOvalX = ovalX + innerMargin;
        int innerOvalY = ovalY + innerMargin;
        g2d.drawArc(innerOvalX, innerOvalY, innerOvalWidth, innerOvalHeight, 0, -180);

        // Leva linija za spoljašnji luk
        int startX1 = x;
        int startY1 = y + rectHeight;
        int endX1 = ovalX;
        int endY1 = ovalY + (ovalHeight / 2);
        g2d.drawLine(startX1, startY1, endX1, endY1);

        // Desna linija za spoljašnji luk
        int startX2 = x + width;
        int startY2 = y + rectHeight;
        int endX2 = ovalX + ovalWidth;
        int endY2 = ovalY + (ovalHeight / 2);
        g2d.drawLine(startX2, startY2, endX2, endY2);

        // Leva linija za unutrašnji luk
        int innerStartX1 = x + innerMargin;
        int innerStartY1 = y + rectHeight + innerMargin;
        int innerEndX1 = innerOvalX;
        int innerEndY1 = innerOvalY + (innerOvalHeight / 2);
        g2d.drawLine(innerStartX1, innerStartY1, innerEndX1, innerEndY1);

        // Desna linija za unutrašnji luk
        int innerStartX2 = x + width - innerMargin;
        int innerStartY2 = y + rectHeight + innerMargin;
        int innerEndX2 = innerOvalX + innerOvalWidth;
        int innerEndY2 = innerOvalY + (innerOvalHeight / 2);
        g2d.drawLine(innerStartX2, innerStartY2, innerEndX2, innerEndY2);

        // Gornja linija za unutrasnji luk
        int innerTopStartX = innerStartX1;
        int innerTopStartY = innerStartY1;
        int innerTopEndX = innerStartX2;
        int innerTopEndY = innerStartY2;
        g2d.drawLine(innerTopStartX, innerTopStartY, innerTopEndX, innerTopEndY);

        applySelectionBorder(g2d);
        g2d.setTransform(originalTransform);
    }

}
