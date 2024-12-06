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
        add(actionManager.getSelectElementsAction());
        add(actionManager.getZoomElementsAction());
        add(actionManager.getResizeElementsAction());
        add(actionManager.getRotateElementRightAction());
        add(actionManager.getRotateElementLeftAction());
        add(actionManager.getCopyElementAction());
        add(actionManager.getPasteElementAction());
        add(actionManager.getDeleteElementAction());

        for (Component component : getComponents()) {
            if (component instanceof AbstractButton button) {
                button.setBorderPainted(false);
            }
        }
    }
}
