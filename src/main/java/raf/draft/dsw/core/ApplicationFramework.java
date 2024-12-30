package raf.draft.dsw.core;

import raf.draft.dsw.gui.swing.model.messages.Logger;
import raf.draft.dsw.gui.swing.model.messages.LoggerFactory;
import raf.draft.dsw.gui.swing.model.messages.MessageGenerator;
import raf.draft.dsw.gui.swing.model.repository.DraftRoomRepository;

public class ApplicationFramework {
    private static ApplicationFramework instance;
    protected DraftRoomRepository draftRoomRepository;
    protected GUI gui;
    protected Serializer serializer;
    protected MessageGenerator messageGenerator;

    public static ApplicationFramework getInstance(){
        if(instance == null)
            instance = new ApplicationFramework();
        return instance;
    }

    private ApplicationFramework(){
    }

    public void initialize(GUI gui, DraftRoomRepository roomRepository, Serializer serializer) {
        this.gui = gui;
        this.serializer = serializer;
        this.draftRoomRepository = roomRepository;
        messageGenerator = new MessageGenerator();

        Logger consoleLogger = LoggerFactory.getLogger("console");
        Logger fileLogger = LoggerFactory.getLogger("file");
        messageGenerator.addSubscriber(consoleLogger);
        messageGenerator.addSubscriber(fileLogger);
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

    public Serializer getSerializer() {
        return serializer;
    }

    public MessageGenerator getMessageGenerator() {
        return messageGenerator;
    }
}
