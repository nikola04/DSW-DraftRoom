package raf.draft.dsw.core;

import raf.draft.dsw.gui.swing.view.MainFrame;

public class ApplicationFramework {
    private static ApplicationFramework instance;
    protected DraftRoomRepository draftRoomRepository;
    protected GUI gui;

    public static ApplicationFramework getInstance(){
        if(instance == null)
            instance = new ApplicationFramework();
        return instance;
    }

    private ApplicationFramework(){
    }

    public void initialize(GUI gui, DraftRoomRepository roomRepository) {
        this.gui = gui;
        this.draftRoomRepository = roomRepository;
    }

    public void run(){
        gui.start();
    }

    public DraftRoomRepository getDraftRoomRepository() {
        return draftRoomRepository;
    }

    public GUI getGui() {
        return gui;
    }
}
