package raf.draft.dsw.gui.swing.model.repository;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.nodes.DraftNodeComposite;
import raf.draft.dsw.gui.swing.model.structures.ProjectExplorer;

public class DraftRoomExplorerImplementation implements DraftRoomRepository {
    private ProjectExplorer projectExplorer;
    public DraftRoomExplorerImplementation() {
        projectExplorer = new ProjectExplorer("My Explorer");
    }

    @Override
    public ProjectExplorer getProjectExplorer() {
        return projectExplorer;
    }

    @Override
    public void addChild(DraftNodeComposite parent, DraftNode child) {

    }
}
