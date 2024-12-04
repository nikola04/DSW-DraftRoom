package raf.draft.dsw.gui.swing.model.repository;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.nodes.DraftNodeComposite;
import raf.draft.dsw.gui.swing.model.nodes.DraftNodeFactory;
import raf.draft.dsw.gui.swing.model.structures.ProjectExplorer;

public class DraftRoomRepositoryImplementation implements DraftRoomRepository {
    private ProjectExplorer projectExplorer;
    public DraftRoomRepositoryImplementation() {
        projectExplorer = new ProjectExplorer("My Explorer");
    }

    @Override
    public ProjectExplorer getProjectExplorer() {
        return projectExplorer;
    }

    @Override
    public DraftNode createNodeFactory(String type, String name, DraftNode parent) {
        return DraftNodeFactory.getDraftNode(type, name, parent);
    }

    @Override
    public void addChild(DraftNodeComposite parent, DraftNode child) {

    }

}
