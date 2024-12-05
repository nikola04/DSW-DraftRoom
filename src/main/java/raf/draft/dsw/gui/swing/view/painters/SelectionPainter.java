package raf.draft.dsw.gui.swing.view.painters;

import raf.draft.dsw.gui.swing.model.structures.RoomElement;

import java.awt.*;

public class SelectionPainter extends ElementPainter {
    public SelectionPainter(RoomElement element) {
        super(element);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(173, 216, 230, 100)); // RGBA (Red, Green, Blue, Alpha)
        g2d.fillRect(element.getX(), element.getY(), element.getWidth(), element.getHeight());

        g2d.setColor(new Color(0, 122, 255)); // RGB
        g2d.setStroke(new BasicStroke(2)); // Set stroke width to 2px
        g2d.drawRect(element.getX(), element.getY(), element.getWidth(), element.getHeight());
        g2d.drawRect(element.getX(), element.getY(), element.getWidth(), element.getHeight());
    }
}
