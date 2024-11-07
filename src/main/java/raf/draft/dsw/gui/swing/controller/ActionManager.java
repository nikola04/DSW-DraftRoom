package raf.draft.dsw.gui.swing.controller;

import raf.draft.dsw.gui.swing.controller.actions.AboutUsAction;
import raf.draft.dsw.gui.swing.controller.actions.ExitAction;
import raf.draft.dsw.gui.swing.tree.controller.DeleteNodeAction;
import raf.draft.dsw.gui.swing.tree.controller.NewNodeAction;
import raf.draft.dsw.gui.swing.tree.controller.RenameNodeAction;

public class ActionManager {
    AboutUsAction aboutUsAction;
    ExitAction exitAction;
    NewNodeAction newNodeAction;
    DeleteNodeAction deleteNodeAction;
    RenameNodeAction renameNodeAction;

    public ActionManager() {
        initialize();
    }
    private void initialize() {
        this.aboutUsAction = new AboutUsAction();
        this.exitAction = new ExitAction();
        this.newNodeAction = new NewNodeAction();
        this.deleteNodeAction = new DeleteNodeAction();
        this.renameNodeAction = new RenameNodeAction();
    }
    public AboutUsAction getAboutUsAction() {
        return aboutUsAction;
    }
    public ExitAction getExitAction() {
        return exitAction;
    }
    public NewNodeAction getNewNodeAction() {
        return newNodeAction;
    }
    public DeleteNodeAction getDeleteNodeAction() { return deleteNodeAction; }
    public RenameNodeAction getRenameNodeAction() { return renameNodeAction; }
}
