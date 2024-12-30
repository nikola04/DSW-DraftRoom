package raf.draft.dsw;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.core.GUI;
import raf.draft.dsw.core.Serializer;
import raf.draft.dsw.gui.swing.SwingGUI;
import raf.draft.dsw.gui.swing.model.repository.DraftRoomRepositoryImplementation;
import raf.draft.dsw.gui.swing.model.repository.DraftRoomRepository;
import raf.draft.dsw.serializer.JacksonSerializer;

public class AppCore {
    public static void main(String[] args) {
        ApplicationFramework appCore = ApplicationFramework.getInstance();
        GUI gui = new SwingGUI();
        DraftRoomRepository draftRep = new DraftRoomRepositoryImplementation();
        Serializer serializer = new JacksonSerializer();
        appCore.initialize(gui, draftRep, serializer);
        appCore.run();
    }
}