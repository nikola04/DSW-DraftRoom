package raf.draft.dsw.gui.swing.view.painters;

import raf.draft.dsw.gui.swing.model.structures.RoomElement;

import java.awt.*;

public class BedPainter extends ElementPainter {
    public BedPainter(RoomElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawRect(element.getX(), element.getY(), element.getWidth(), element.getHeight());
        int pillowWidth = element.getWidth() / 3;
        int pillowHeight = element.getHeight() / 4;
        int pillowX = element.getX() + (element.getWidth() - pillowWidth) / 2;
        int pillowY = element.getY();
        g.drawRect(pillowX, pillowY, pillowWidth, pillowHeight);
        applyTransformations(g2d);
    }
}
