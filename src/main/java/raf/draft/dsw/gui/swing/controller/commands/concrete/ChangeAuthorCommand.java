package raf.draft.dsw.gui.swing.controller.commands.concrete;

import raf.draft.dsw.gui.swing.controller.commands.AbstractCommand;
import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.structures.Project;

public class ChangeAuthorCommand extends AbstractCommand {
    Project project;
    String oldName;
    String newName;
    public ChangeAuthorCommand(Project project, String oldName, String newName) {
        this.project = project;
        this.oldName = oldName;
        this.newName = newName;
    }
    @Override
    public void doCommand() {
        project.setAuthor(newName);
    }

    @Override
    public void undoCommand() {
        project.setAuthor(oldName);
    }
}
