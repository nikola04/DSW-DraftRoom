package raf.draft.dsw.core;

import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.nodes.DraftNodeComposite;
import raf.draft.dsw.gui.swing.model.structures.ProjectExplorer;

public interface DraftRoomRepository {
    ProjectExplorer getProjectExplorer();
    void addChild(DraftNodeComposite parent, DraftNode child);
}
