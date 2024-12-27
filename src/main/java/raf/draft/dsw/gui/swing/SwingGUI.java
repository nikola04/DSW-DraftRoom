package raf.draft.dsw.gui.swing;

import raf.draft.dsw.core.GUI;
import raf.draft.dsw.gui.swing.controller.commands.CommandManager;
import raf.draft.dsw.gui.swing.view.MainFrame;

public class SwingGUI implements GUI {
    private MainFrame mainFrameInstance;
    private CommandManager commandManager;

    @Override
    public void start() {
        mainFrameInstance = MainFrame.getInstance();
        commandManager = new CommandManager();
        disableRedoAction();
        disableUndoAction();
        mainFrameInstance.setVisible(true);
    }

    @Override
    public CommandManager getCommandManager() {
        return this.commandManager;
    }

    @Override
    public void enableUndoAction() {
        MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(true);
    }

    @Override
    public void disableUndoAction() {
        MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(false);
    }

    @Override
    public void enableRedoAction() {
        MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(true);
    }

    @Override
    public void disableRedoAction() {
        MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(false);
    }
}
