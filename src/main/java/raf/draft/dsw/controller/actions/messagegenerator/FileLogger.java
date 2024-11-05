package raf.draft.dsw.controller.actions.messagegenerator;

import raf.draft.dsw.model.messages.Message;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger {
    private static final String FILE_PATH = "src/main/resources/log.txt";

    @Override
    public void log(Message message) {
        try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
            writer.write(message.toString() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
