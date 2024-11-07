package raf.draft.dsw.gui.swing.controller.messagegenerator;

import raf.draft.dsw.gui.swing.model.messages.Message;

public class ConsoleLogger implements Logger {
    @Override
    public void log(Message message) {
        System.out.println(message.toString());
    }
}
