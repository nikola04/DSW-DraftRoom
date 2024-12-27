package raf.draft.dsw.gui.swing.view;

import javax.swing.*;
import java.awt.*;

public class ToolBar extends JToolBar {
    public ToolBar() {
        super(HORIZONTAL);
        setFloatable(false);
        setBorderPainted(false);

        add(MainFrame.getInstance().getActionManager().getExitAction());
        add(MainFrame.getInstance().getActionManager().getNewNodeAction());
        add(MainFrame.getInstance().getActionManager().getRenameNodeAction());
        add(MainFrame.getInstance().getActionManager().getChangeNodePathAction());
        add(MainFrame.getInstance().getActionManager().getChangeNodeAuthorAction());
        add(MainFrame.getInstance().getActionManager().getDeleteNodeAction());
        add(MainFrame.getInstance().getActionManager().getUndoAction());
        add(MainFrame.getInstance().getActionManager().getRedoAction());

        for (Component component : getComponents()) {
            if (component instanceof AbstractButton button) {
                button.setBorderPainted(false);
            }
        }
    }
}
