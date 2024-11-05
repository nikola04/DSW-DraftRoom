package raf.draft.dsw.core;

import raf.draft.dsw.model.nodes.DraftNode;
import raf.draft.dsw.model.nodes.DraftNodeComposite;
import raf.draft.dsw.model.structures.ProjectExplorer;

public interface DraftRoomRepository {
    ProjectExplorer getProjectExplorer();
    void addChild(DraftNodeComposite parent, DraftNode child);
}
