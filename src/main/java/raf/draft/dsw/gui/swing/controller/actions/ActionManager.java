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
    private EditElementAction editElementAction;
    private UndoAction undoAction;
    private RedoAction redoAction;
    private OpenAction openAction;
    private SaveAction saveAction;
    private SaveAsAction saveAsAction;

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
        this.editElementAction = new EditElementAction();
        this.undoAction = new UndoAction();
        this.redoAction = new RedoAction();
        this.openAction = new OpenAction();
        this.saveAction = new SaveAction();
        this.saveAsAction = new SaveAsAction();
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
    public EditElementAction getEditElementAction() { return editElementAction; }
    public UndoAction getUndoAction() { return undoAction; }
    public RedoAction getRedoAction() { return redoAction; }
    public OpenAction getOpenAction() { return openAction; }
    public SaveAction getSaveAction() { return saveAction; }
    public SaveAsAction getSaveAsAction() { return saveAsAction; }
}
