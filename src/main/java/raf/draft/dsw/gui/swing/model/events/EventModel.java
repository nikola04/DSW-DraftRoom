package raf.draft.dsw.gui.swing.model.events;

public class EventModel {
    EventType type;
    Object value;
    public EventModel(EventType type, Object value) {
        this.type = type;
        this.value = value;
    }
    public EventType getType() {
        return type;
    }
    public Object getValue() {
        return value;
    }
}
