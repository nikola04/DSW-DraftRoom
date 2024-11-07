package raf.draft.dsw.gui.swing.controller.messagegenerator;

import raf.draft.dsw.gui.swing.controller.observer.IPublisher;
import raf.draft.dsw.gui.swing.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.model.messages.Message;
import raf.draft.dsw.gui.swing.model.messages.MessageType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageGenerator implements IPublisher {
    private List<ISubscriber> subscribers = new ArrayList<>();


    @Override
    public void addSubscriber(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void publish(Message message) {
        for (ISubscriber subscriber : subscribers) {
            subscriber.update(message);
        }
    }

    public void generateMessage(String content, MessageType type) {
        Message message = new Message(content, type, LocalDateTime.now());
        publish(message);
    }
}
