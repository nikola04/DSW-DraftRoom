package raf.draft.dsw.gui.swing.controller;

import raf.draft.dsw.gui.swing.controller.actions.AboutUsAction;
import raf.draft.dsw.gui.swing.controller.actions.ExitAction;
import raf.draft.dsw.gui.swing.tree.controller.DeleteNodeAction;
import raf.draft.dsw.gui.swing.tree.controller.NewNodeAction;

public class ActionManager {
    AboutUsAction aboutUsAction;
    ExitAction exitAction;
    NewNodeAction newNodeAction;
    DeleteNodeAction deleteNodeAction;

    public ActionManager() {
        initialize();
    }
    private void initialize() {
        this.aboutUsAction = new AboutUsAction();
        this.exitAction = new ExitAction();
        this.newNodeAction = new NewNodeAction();
        this.deleteNodeAction = new DeleteNodeAction();
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
}
