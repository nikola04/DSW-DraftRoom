package raf.draft.dsw.gui.swing.model.utils;

import java.awt.*;

public class ColorUtil {
    public static String toHex(java.awt.Color color) {
        if (color == null)
            return "#ffffff";
        return String.format("#%02X%02X%02X", color.getRed(), color.getGreen(), color.getBlue());
    }
    public static Color fromHex(String hex) {
        if (hex == null || hex.isEmpty())
            return Color.WHITE;
        if (hex.startsWith("#"))
            hex = hex.substring(1);
        int rgb = Integer.parseInt(hex, 16);
        return new Color((rgb >> 16) & 0xFF, (rgb >> 8) & 0xFF, rgb & 0xFF);
    }
}
