package raf.draft.dsw.gui.swing.model.messages;

public class LoggerFactory {
    public static Logger getLogger(String type) {
        switch (type.toLowerCase()) {
            case "console":
                return new ConsoleLogger();
            case "file":
                return new FileLogger();
            default:
                throw new IllegalArgumentException("Unknown logger type");
        }
    }
}
