package raf.draft.dsw.gui.swing.model.structures;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.controller.commands.concrete.DeleteCommand;
import raf.draft.dsw.gui.swing.controller.commands.concrete.PasteCopiedCommand;
import raf.draft.dsw.gui.swing.controller.observer.IPublisher;
import raf.draft.dsw.gui.swing.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.model.events.EventModel;
import raf.draft.dsw.gui.swing.model.events.EventType;
import raf.draft.dsw.gui.swing.model.messages.MessageType;
import raf.draft.dsw.gui.swing.model.nodes.DraftNode;
import raf.draft.dsw.gui.swing.model.nodes.DraftNodeComposite;
import raf.draft.dsw.gui.swing.model.structures.elements.Selection;
import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;
import raf.draft.dsw.gui.swing.view.MainFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Room extends DraftNodeComposite implements IPublisher {
    private final List<ISubscriber> subscribers = new ArrayList<>();
    private int width, height; //cm
    private double scaleFactor = 1.0;
    private boolean dimensionsSet = false;
    private Selection selectionElement = null;
    private double pxConversionRatio = 1;
    private List<RoomElement> selectedElements = new ArrayList<>();
    private final List<RoomElement> copiedElements = new ArrayList<>();
    public Room(String name, DraftNode parent) {
        super(name, parent);
    }
    public void setDimensions(int width, int height, int panelWidth, int panelHeight) {
        this.width = width;
        this.height = height;
        this.scaleFactor = 1;
        this.pxConversionRatio = calculatePxRatio(panelWidth, panelHeight);
        this.dimensionsSet = true;
        publish(new EventModel(EventType.DIMENSION_CHANGE, null));
    }
    private double calculatePxRatio(int panelWidth, int panelHeight) {
        double ratioX = (double) panelWidth / width;
        double ratioY = (double) panelHeight / height;
        return Math.min(ratioX, ratioY);
    }
    public boolean isPointInsideRoom(Point point) {
        return point.x >= 0 && point.x < width && point.y >= 0 && point.y < height;
    }
    public boolean canPlaceElement(RoomElement newElement) {
        for (DraftNode node : this.getChildren()) {
            RoomElement element = (RoomElement) node;
            if(element.equals(newElement)) continue;
            if (element.overlaps(newElement.getRotatedBounds())) {
                return false;
            }
        }
        return true;
    }
    public boolean isNotInsideRoom(RoomElement element) {
        Rectangle rotatedBounds = element.getRotatedBounds();
        return rotatedBounds.getMinX() < 0 || rotatedBounds.getMinY() < 0 || rotatedBounds.getMaxX() > this.getWidth() || rotatedBounds.getMaxY() > this.getHeight();
    }
    public List<RoomElement> overlappedElements(Rectangle rect) {
        List<RoomElement> elements = new ArrayList<>();
        for (DraftNode node : super.getChildren()) {
            RoomElement element = (RoomElement) node;
            if (element.overlaps(rect)) elements.add(element);
        }
        return elements;
    }
    public void rotateElements(List<RoomElement> roomElements, int rotation) {
        for (RoomElement element : roomElements) {
            element.setRotateRatio(element.getRotateRatio() + rotation);
            if(!canPlaceElement(element) ||
                    (element.getRotatedBounds().x < 0 || element.getRotatedBounds().y < 0 || element.getRotatedBounds().x + element.getRotatedBounds().width > width || element.getRotatedBounds().y + element.getRotatedBounds().height > height)
            ) element.setRotateRatio(element.getRotateRatio() + rotation); // rotate by another 90 to reverse because cant fit or overlap
        }
        publish(new EventModel(EventType.REPAINT, null));
    }
    public void setSelectedElements(List<RoomElement> selectedElements) {
        this.selectedElements = selectedElements;
        for (RoomElement element : selectedElements) {
            element.setSelected(true);
        }
        publish(new EventModel(EventType.REPAINT, null));
    }
    public void resetSelected(){
        for (RoomElement element : selectedElements) {
            element.setSelected(false);
        }
        selectedElements.clear();
        publish(new EventModel(EventType.REPAINT, null));
    }
    public void deleteSelectedElements() {
        if(!selectedElements.isEmpty()) {
            DeleteCommand deleteCommand = new DeleteCommand(selectedElements, this);
            ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(deleteCommand);
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("You have deleted elements successfully", MessageType.INFO);
        }
        this.selectedElements.clear();
    }
    public void copySelectedElements() {
        copiedElements.clear();
        copiedElements.addAll(selectedElements);
    }
    public void cloneCopiedElements(){
        List<RoomElement> clones = new ArrayList<>();
        for(RoomElement element : copiedElements) {
            RoomElement clonedElement = (RoomElement) element.clone();
            int offsetX = element.getLogicalWidth() / 10, offsetY = element.getLogicalHeight() / 10;
            if(element.getLogicalX() + element.getLogicalWidth() + offsetX > width) { // if new element is beyond room
                offsetX *= -1;
                if(element.getLogicalX() + offsetX < 0) // try opposite offset or set to x=0
                    clonedElement.setX(0);
                else clonedElement.setX(element.getLogicalX() + offsetX);
            }else clonedElement.setX(element.getLogicalX() + offsetX);
            if(element.getLogicalY() + element.getLogicalHeight() + offsetY > height) {
                offsetY *= -1;
                if(element.getLogicalY() + offsetY < 0)
                    clonedElement.setY(0);
                else clonedElement.setX(element.getLogicalY() + offsetY);
            }
            clonedElement.setY(element.getLogicalY() + offsetY);

            clones.add(clonedElement);
        }
        PasteCopiedCommand pasteCommand = new PasteCopiedCommand(this, clones);
        ApplicationFramework.getInstance().getGui().getCommandManager().addCommand(pasteCommand);
//        publish(null);
    }
    public void setSelectionElement(Selection selectionElement) {
        this.selectionElement = selectionElement;
        publish(null);
    }
    public boolean isDimensionsSet() {
        return dimensionsSet;
    }
    public Selection getSelectionElement() {
        return selectionElement;
    }

    public List<RoomElement> getSelectedElements() {
        return selectedElements;
    }

    @Override
    public void removeChild(DraftNode node) {
        super.removeChild(node);
        publish(null);
    }

    @Override
    public void addChild(DraftNode node) {
        super.addChild(node);
        publish(null);
    }

    @Override
    public List<DraftNode> getChildren() {
        List<DraftNode> children = new ArrayList<>(super.getChildren());
        if(selectionElement != null) children.add(selectionElement);
        return children;
    }

    public List<RoomElement> getElements() {
        List<RoomElement> elements = new ArrayList<>();
        for(DraftNode node : super.getChildren())
            elements.add((RoomElement) node);
        return elements;
    }

    @Override
    public void addSubscriber(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    @Override
    public void removeSubscriber(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }
    public Color getColor() {
        if(this.getParent() instanceof Building building)
            return building.getColor();
        return Color.WHITE;
    }

    public int getWidth() {
        return width;
    }

    public double getPxConversionRatio() {
        return pxConversionRatio;
    }

    public int getHeight() {
        return height;
    }

    public double getScaleFactor() {
        return scaleFactor;
    }

    public void setScaleFactor(double scaleFactor) {
        double min = 0.05;
        double max =  20;
        this.scaleFactor = Math.max(min, Math.min(scaleFactor, max));
    }

    @Override
    public void publish(Object value) {
        for (ISubscriber subscriber : subscribers) {
            subscriber.update(value);
        }
    }
}