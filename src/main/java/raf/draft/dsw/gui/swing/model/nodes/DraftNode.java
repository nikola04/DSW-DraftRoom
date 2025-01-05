package raf.draft.dsw.gui.swing.model.nodes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import raf.draft.dsw.gui.swing.model.structures.Building;
import raf.draft.dsw.gui.swing.model.structures.Project;
import raf.draft.dsw.gui.swing.model.structures.Room;
import raf.draft.dsw.gui.swing.model.structures.elements.*;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
        property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Room.class, name = "Room"),
        @JsonSubTypes.Type(value = Building.class, name = "Building"),
        @JsonSubTypes.Type(value = Project.class, name = "Project"),
        @JsonSubTypes.Type(value = Bath.class, name = "Bath"),
        @JsonSubTypes.Type(value = Bed.class, name = "Bed"),
        @JsonSubTypes.Type(value = Boiler.class, name = "Boiler"),
        @JsonSubTypes.Type(value = Door.class, name = "Door"),
        @JsonSubTypes.Type(value = Sink.class, name = "Sink"),
        @JsonSubTypes.Type(value = Table.class, name = "Table"),
        @JsonSubTypes.Type(value = Toilet.class, name = "Toilet"),
        @JsonSubTypes.Type(value = Wardrobe.class, name = "Wardrobe"),
        @JsonSubTypes.Type(value = WashingMachine.class, name = "WashingMachine"),
        @JsonSubTypes.Type(value = Selection.class, name = "Selection"),
})
public abstract class DraftNode {
    protected String name;
    @JsonIgnore
    protected DraftNode parent;

    public DraftNode(String name, DraftNode parent) {
        this.name = name;
        this.parent = parent;
    }

    public abstract void addChild(DraftNode child);
    public abstract void removeChild(DraftNode child);
    public String getName() {
        return name;
    }
    public DraftNode getParent() {
        return parent;
    }

    public void setParent(DraftNode parent) {
        this.parent = parent;
    }

    public void onAppliedChange() {
        Project p = findParentProject();
        if (p != null) p.appliedChange();
    }
    public Project findParentProject(){
        return findProjectRecursive(this);
    }
    private Project findProjectRecursive(DraftNode node) {
        if(node == null || node instanceof Project) return (Project) node;
        return findProjectRecursive(node.getParent());
    }

    public void setName(String name) {
        this.name = name;
        onAppliedChange();
    }
}
