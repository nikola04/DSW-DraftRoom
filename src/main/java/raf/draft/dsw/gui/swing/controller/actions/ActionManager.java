package raf.draft.dsw.gui.swing.controller.actions;

public class ActionManager {
    private AboutUsAction aboutUsAction;
    private ExitAction exitAction;
    private NewNodeAction newNodeAction;
    private DeleteNodeAction deleteNodeAction;
    private RenameNodeAction renameNodeAction;
    private ChangeNodePathAction changeNodePathAction;
    private ChangeNodeAuthorAction changeNodeAuthorAction;
    private AddElementAction addElementAction;
    private ZoomElementsAction zoomElementsAction;
    private RotateElementLeftAction rotateElementLeftAction;
    private RotateElementRightAction rotateElementRightAction;
    private SelectElementsAction selectElementsAction;
    private DeleteElementAction deleteElementAction;
    private CopyElementAction copyElementAction;
    private PasteElementAction pasteElementAction;
    private ResizeElementsAction resizeElementsAction;
    private MoveElementsAction moveElementsAction;

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
        this.addElementAction = new AddElementAction();
        this.zoomElementsAction = new ZoomElementsAction();
        this.rotateElementLeftAction = new RotateElementLeftAction();
        this.rotateElementRightAction = new RotateElementRightAction();
        this.selectElementsAction = new SelectElementsAction();
        this.deleteElementAction = new DeleteElementAction();
        this.copyElementAction = new CopyElementAction();
        this.pasteElementAction = new PasteElementAction();
        this.resizeElementsAction = new ResizeElementsAction();
        this.moveElementsAction = new MoveElementsAction();
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
    public AddElementAction getAddElementAction() { return addElementAction; }
    public ZoomElementsAction getZoomElementsAction() { return zoomElementsAction; }
    public RotateElementLeftAction getRotateElementLeftAction() { return rotateElementLeftAction; }
    public RotateElementRightAction getRotateElementRightAction() { return rotateElementRightAction; }
    public SelectElementsAction getSelectElementsAction() { return selectElementsAction; }
    public DeleteElementAction getDeleteElementAction() { return deleteElementAction; }
    public CopyElementAction getCopyElementAction() { return copyElementAction; }
    public PasteElementAction getPasteElementAction() { return pasteElementAction; }
    public ResizeElementsAction getResizeElementsAction() { return resizeElementsAction; }
    public MoveElementsAction getMoveElementsAction() { return moveElementsAction; }
}
