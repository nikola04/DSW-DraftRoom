package raf.draft.dsw.gui.swing.model.messages;

public class LoggerFactory {
    public static Logger getLogger(String type) {
        return switch (type.toLowerCase()) {
            case "console" -> new ConsoleLogger();
            case "file" -> new FileLogger();
            default -> throw new IllegalArgumentException("Unknown logger type");
        };
    }
}
