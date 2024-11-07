package raf.draft.dsw.gui.swing.controller.observer;

import raf.draft.dsw.gui.swing.model.messages.Message;

public interface ISubscriber {
    void update(Message message);
}
