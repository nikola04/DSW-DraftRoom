package raf.draft.dsw.gui.swing.controller.messagegenerator;

import raf.draft.dsw.gui.swing.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.model.messages.Message;

public interface Logger extends ISubscriber{
    void log(Message message);
}
