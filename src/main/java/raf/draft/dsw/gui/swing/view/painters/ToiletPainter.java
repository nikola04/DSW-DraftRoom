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
        int rectHeight = height / 3; // Pravougaonik zauzima 1/3 visine
        g2d.drawRect(x, y, width, rectHeight);

        // Donji luk
        int ovalWidth = width;
        int ovalHeight = (2 * height) / 3; // Luk zauzima 2/3 visine
        int ovalX = x;
        int ovalY = y + rectHeight; // Počinje odmah ispod pravougaonika
        g2d.drawArc(ovalX, ovalY, ovalWidth, ovalHeight, 0, -180); // Polukrug

        // Spajajuće linije
        // Leva linija
        int startX1 = x; // Leva ivica pravougaonika
        int startY1 = y + rectHeight; // Donja leva tačka pravougaonika
        int endX1 = x; // Leva tačka luka
        int endY1 = ovalY; // Gornja leva tačka luka
        g2d.drawLine(startX1, startY1, endX1, endY1);

        // Desna linija
        int startX2 = x + width; // Desna ivica pravougaonika
        int startY2 = y + rectHeight; // Donja desna tačka pravougaonika
        int endX2 = x + width; // Desna tačka luka
        int endY2 = ovalY; // Gornja desna tačka luka
        g2d.drawLine(startX2, startY2, endX2, endY2);

        // Unutrašnji manji luk (grb)
        int innerMargin = 10;
        int innerOvalWidth = ovalWidth - 2 * innerMargin;
        int innerOvalHeight = ovalHeight - 2 * innerMargin;
        int innerOvalX = ovalX + innerMargin;
        int innerOvalY = ovalY + innerMargin;
        g2d.drawArc(innerOvalX, innerOvalY, innerOvalWidth, innerOvalHeight, 0, -180);

        applySelectionBorder(g2d);
        g2d.setTransform(originalTransform);
    }
}
