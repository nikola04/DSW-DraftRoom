package raf.draft.dsw.gui.swing.view.painters;

import raf.draft.dsw.gui.swing.model.structures.RoomElement;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class TablePainter extends ElementPainter {
    public TablePainter(RoomElement element) {
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

        int margin = 10;
        g2d.fillOval(x + margin, y + margin, 5, 5); // Levo gore
        g2d.fillOval(x + width - margin, y + margin, 5, 5); // Desno gore
        g2d.fillOval(x + margin, y + height - margin, 5, 5); // Levo dole
        g2d.fillOval(x + width - margin, y + height - margin, 5, 5); // Desno dole

        applySelectionBorder(g2d);
        g2d.setTransform(originalTransform);
    }
}
