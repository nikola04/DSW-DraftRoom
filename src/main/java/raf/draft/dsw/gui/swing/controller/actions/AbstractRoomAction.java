package raf.draft.dsw.gui.swing.controller.actions;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public abstract class AbstractRoomAction extends AbstractAction {
    public Icon loadIcon(String path){
        Icon icon = null;
        URL ImageURL = getClass().getResource(path);
        if(ImageURL != null)
        {
            Image img = new ImageIcon(ImageURL).getImage();
            Image newImg = img.getScaledInstance(27, 27, Image.SCALE_SMOOTH);
            icon = new ImageIcon(newImg);
        }
        else
        {
            System.err.println("File " + path + " not found");
        }
        return icon;

    }}
