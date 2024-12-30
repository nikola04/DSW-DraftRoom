package raf.draft.dsw.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.core.Serializer;
import raf.draft.dsw.gui.swing.model.messages.MessageType;
import raf.draft.dsw.gui.swing.model.structures.Project;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JacksonSerializer implements Serializer {
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public Project loadProject(File file) {
        return null;
    }

    @Override
    public void saveProject(Project project) {
        String path = project.getPath();
        try (FileWriter writer = new FileWriter(path)) {
            objectMapper.writeValue(writer, project);
        } catch (IOException e) {
            e.printStackTrace();
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Error saving project.", MessageType.ERROR);
        }
    }
}
