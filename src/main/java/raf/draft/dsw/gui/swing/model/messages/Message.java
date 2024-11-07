package raf.draft.dsw.gui.swing.model.messages;

import java.time.LocalDateTime;

public class Message {
    private String content;
    private MessageType type;
    private LocalDateTime timestamp;

    public Message(String content, MessageType type, LocalDateTime timestamp) {
        this.content = content;
        this.type = type;
        this.timestamp = timestamp;
    }
    public MessageType getType() {
        return type;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", type, timestamp, content);
    }
}
