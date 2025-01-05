package raf.draft.dsw.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.core.Serializer;
import raf.draft.dsw.gui.swing.model.messages.MessageType;
import raf.draft.dsw.gui.swing.model.structures.Project;
import raf.draft.dsw.gui.swing.model.structures.Room;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JacksonSerializer implements Serializer {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public Project loadProject(File file) {
        try (FileReader fileReader = new FileReader(file)) {
            return objectMapper.readValue(fileReader, Project.class);
        } catch (IOException e) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Error loading project.", MessageType.ERROR);
            return null;
        }
    }

    @Override
    public void saveProject(Project project) {
        String path = project.getPath();
        try (FileWriter writer = new FileWriter(path)) {
            objectMapper.writeValue(writer, project);
        } catch (IOException e) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Error saving project.", MessageType.ERROR);
        }
    }

    @Override
    public void saveTemplate(Room room, String path) {
        try (FileWriter writer = new FileWriter(path)) {
            objectMapper.writeValue(writer, room);
        } catch (IOException e) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Error saving template.", MessageType.ERROR);
        }
    }

    @Override
    public Room loadTemplate(File file) {
        try (FileReader fileReader = new FileReader(file)) {
            return objectMapper.readValue(fileReader, Room.class);
        } catch (IOException e) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Error loading template.", MessageType.ERROR);
            return null;
        }
    }
}
