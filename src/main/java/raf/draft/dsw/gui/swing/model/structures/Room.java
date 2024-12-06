package raf.draft.dsw.gui.swing.model.structures;

import raf.draft.dsw.core.ApplicationFramework;
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
    private double initialScaleFactor = 1.0;
    private boolean dimensionsSet = false;
    private Selection selectionElement = null;
    private List<RoomElement> selectedElements = new ArrayList<>();
    private List<RoomElement> copiedElements = new ArrayList<>();
    public Room(String name, DraftNode parent) {
        super(name, parent);
    }
    public void setDimensions(int width, int height, int panelWidth, int panelHeight) {
        this.width = width;
        this.height = height;
        this.scaleFactor = calculateScaleFactor(panelWidth, panelHeight);
        this.initialScaleFactor = scaleFactor;
        this.dimensionsSet = true;
        publish(new EventModel(EventType.DIMENSION_CHANGE, null));
    }
    private double calculateScaleFactor(int panelWidth, int panelHeight) {
        double scaleFactorX = (double) panelWidth / width;
        double scaleFactorY = (double) panelHeight / height;
        return Math.min(scaleFactorX, scaleFactorY);
    }
    public boolean isPointInsideRoom(Point point) {
        return point.x >= 0 && point.x < width && point.y >= 0 && point.y < height;
    }
    public boolean canPlaceElement(RoomElement newElement) {
        for (DraftNode node : this.getChildren()) {
            RoomElement element = (RoomElement) node;
            if (element.overlaps(newElement.getX(), newElement.getY(), newElement.getWidth(), newElement.getHeight())) {
                return false;
            }
        }
        return true;
    }
    public List<RoomElement> overlappedElements(int x1, int y1, int width, int height) {
        List<RoomElement> elements = new ArrayList<>();
        for (DraftNode node : super.getChildren()) {
            System.out.println(node);
            RoomElement element = (RoomElement) node;
            if (element.overlaps(x1, y1, width, height)) elements.add(element);
        }
        return elements;
    }
    public void rotateSelectedElements(int rotation) {
        for (RoomElement element : selectedElements) {
            element.setRotateRatio(element.getRotateRatio() + rotation);
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
        for(RoomElement element : selectedElements) {
            DraftTreeItem item = MainFrame.getInstance().getDraftTree().findTreeItem(element);
            if(item != null) MainFrame.getInstance().getDraftTree().removeNodeSilently(item);
            super.removeChild(element);
        }
        if(!selectedElements.isEmpty()) {
            publish(null);
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("You have deleted elements successfully", MessageType.INFO);
        }
        this.selectedElements.clear();
    }
    public void copySelectedElements() {
        copiedElements.clear();
        copiedElements.addAll(selectedElements);
    }
    public void cloneCopiedElements(){
        for(RoomElement element : copiedElements) {
            RoomElement clonedElement = element.clone();
            int offsetX = 20, offsetY = 20;
            if(element.getX() + element.getWidth() + 20 > width)
                offsetX *= -1;
            if(element.getY() + element.getHeight() + 20 > height)
                offsetY *= -1;
            clonedElement.setX(element.getX() + offsetX);
            clonedElement.setY(element.getY() + offsetY);
            DraftTreeItem roomTreeItem = MainFrame.getInstance().getDraftTree().findTreeItem(this);
            MainFrame.getInstance().getDraftTree().addChild(roomTreeItem, clonedElement);
            super.addChild(clonedElement);
        }
        publish(null);
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

    public int getHeight() {
        return height;
    }

    public double getScaleFactor() {
        return scaleFactor;
    }

    public void setScaleFactor(double scaleFactor) {
        double min = 0.01 + initialScaleFactor / 10;
        double max = initialScaleFactor * 10;
        scaleFactor = Math.max(min, Math.min(scaleFactor, max));
        this.scaleFactor = scaleFactor;
    }

    @Override
    public void publish(Object value) {
        for (ISubscriber subscriber : subscribers) {
            subscriber.update(value);
        }
    }
}