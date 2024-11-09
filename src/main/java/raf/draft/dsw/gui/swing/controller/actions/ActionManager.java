package raf.draft.dsw.gui.swing.controller.actions;

public class ActionManager {
    private AboutUsAction aboutUsAction;
    private ExitAction exitAction;
    private NewNodeAction newNodeAction;
    private DeleteNodeAction deleteNodeAction;
    private RenameNodeAction renameNodeAction;
    private ChangeNodePathAction changeNodePathAction;
    private ChangeNodeAuthorAction changeNodeAuthorAction;

    public ActionManager() {
        initialize();
    }
    private void initialize() {
        this.aboutUsAction = new AboutUsAction();
        this.exitAction = new ExitAction();
        this.newNodeAction = new NewNodeAction();
        this.deleteNodeAction = new DeleteNodeAction();
        this.renameNodeAction = new RenameNodeAction();
        this.changeNodePathAction = new ChangeNodePathAction();
        this.changeNodeAuthorAction = new ChangeNodeAuthorAction();
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
    public ChangeNodePathAction getChangeNodePathAction() { return changeNodePathAction; }
    public ChangeNodeAuthorAction getChangeNodeAuthorAction() { return changeNodeAuthorAction; }
}
