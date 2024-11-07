package raf.draft.dsw.gui.swing.view;

import raf.draft.dsw.gui.swing.controller.ActionManager;
import raf.draft.dsw.gui.swing.controller.actions.ExitAction;

import javax.swing.*;

public class ToolBar extends JToolBar {
    public ToolBar() {
        super(HORIZONTAL);
        setFloatable(false);

        add(MainFrame.getInstance().getActionManager().getExitAction());
        add(MainFrame.getInstance().getActionManager().getNewNodeAction());
        add(MainFrame.getInstance().getActionManager().getRenameNodeAction());
        add(MainFrame.getInstance().getActionManager().getDeleteNodeAction());
    }
}
