package raf.draft.dsw.gui.swing.tree.view;

import raf.draft.dsw.gui.swing.model.structures.elements.RoomElement;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.structures.Building;
import raf.draft.dsw.gui.swing.model.structures.Project;
import raf.draft.dsw.gui.swing.model.structures.ProjectExplorer;
import raf.draft.dsw.gui.swing.model.structures.Room;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class DraftTreeCellRenderer extends DefaultTreeCellRenderer {
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row, hasFocus);
        URL imageURL = null;
        DraftNode draftNode = ((DraftTreeItem) value).getDraftNode();
        if (draftNode instanceof ProjectExplorer)
            imageURL = getClass().getResource("/images/folder.png");
        else if (draftNode instanceof Project)
            imageURL = getClass().getResource("/images/project.png");
        else if(draftNode instanceof Building)
            imageURL = getClass().getResource("/images/building.png");
        else if(draftNode instanceof Room)
            imageURL = getClass().getResource("/images/room.png");
        else if(draftNode instanceof RoomElement)
            imageURL = getClass().getResource("/images/room.png"); //temp

        Icon icon = null;
        if (imageURL != null)
            icon = new ImageIcon(imageURL);
        setIcon(icon);

        return this;
    }
}
