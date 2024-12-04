package raf.draft.dsw.gui.swing.view;

import raf.draft.dsw.gui.swing.controller.actions.ActionManager;

import javax.swing.*;
import java.awt.*;

public class RoomToolBar extends JToolBar {
    public RoomToolBar(ActionManager actionManager) {
        super(VERTICAL);
        setFloatable(false);
        setBorderPainted(false);

        add(actionManager.getAddElementAction());

        for (Component component : getComponents()) {
            if (component instanceof AbstractButton button) {
                button.setBorderPainted(false);
            }
        }
    }
}
