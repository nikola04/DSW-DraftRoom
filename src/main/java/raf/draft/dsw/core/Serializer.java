package raf.draft.dsw.core;

import raf.draft.dsw.gui.swing.model.structures.Project;

import java.io.File;

public interface Serializer {
    Project loadProject(File file);
    void saveProject(Project project);
}
