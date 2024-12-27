package raf.draft.dsw.core;

import raf.draft.dsw.gui.swing.controller.commands.CommandManager;

public interface GUI {
    void start();
    CommandManager getCommandManager();
    void enableUndoAction();
    void disableUndoAction();
    void enableRedoAction();
    void disableRedoAction();
}
