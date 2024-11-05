package raf.draft.dsw.gui.swing.controller.observer;

import raf.draft.dsw.model.messages.Message;

public interface IPublisher {
    void addSubscriber(ISubscriber subscriber);
    void removeSubscriber(ISubscriber subscriber);
    void publish(Message message);
}
