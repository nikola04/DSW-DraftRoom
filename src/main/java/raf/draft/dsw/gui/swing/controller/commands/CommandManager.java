package raf.draft.dsw.gui.swing.controller.commands;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.view.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private List<AbstractCommand> commands = new ArrayList<>();
    private int currentCommand = 0;

    public void addCommand(AbstractCommand command) {
        while (currentCommand < commands.size()) {
            commands.remove(currentCommand);
        }
        commands.add(command);
        this.doCommand();
    }

    public void doCommand() {
        if(currentCommand < commands.size()){
            commands.get(currentCommand++).doCommand();
            ApplicationFramework.getInstance().getGui().enableUndoAction();
//            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getDraftTree().getTreeView()); // could be needed when commands are removing elements
        }
        if(currentCommand == commands.size())
            ApplicationFramework.getInstance().getGui().disableRedoAction();
    }

    public void undoCommand() {
        if(currentCommand > 0){
            commands.get(--currentCommand).undoCommand();
            ApplicationFramework.getInstance().getGui().enableRedoAction();
//            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getDraftTree().getTreeView());
        }
        if(currentCommand == 0)
            ApplicationFramework.getInstance().getGui().disableUndoAction();
    }
}