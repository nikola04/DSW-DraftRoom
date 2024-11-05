package raf.draft.dsw.controller.actions.messagegenerator;

import raf.draft.dsw.model.messages.Message;

public class ConsoleLogger implements Logger {
    @Override
    public void log(Message message) {
        System.out.println(message.toString());
    }
}
