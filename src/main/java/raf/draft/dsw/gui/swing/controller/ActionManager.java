package raf.draft.dsw.gui.swing.controller;

import raf.draft.dsw.gui.swing.controller.actions.AboutUsAction;
import raf.draft.dsw.gui.swing.controller.actions.ExitAction;
import raf.draft.dsw.gui.swing.tree.controller.NewProjectAction;

public class ActionManager {
    AboutUsAction aboutUsAction;
    ExitAction exitAction;
    NewProjectAction newProjectAction;

    public ActionManager() {
        initialize();
    }
    private void initialize() {
        this.aboutUsAction = new AboutUsAction();
        this.exitAction = new ExitAction();
        this.newProjectAction = new NewProjectAction();
    }
    public AboutUsAction getAboutUsAction() {
        return aboutUsAction;
    }
    public ExitAction getExitAction() {
        return exitAction;
    }
    public NewProjectAction getNewProjectAction() {
        return newProjectAction;
    }
}
