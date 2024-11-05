package raf.draft.dsw.gui.swing.tree.controller;

import raf.draft.dsw.gui.swing.tree.model.DraftTreeItem;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DraftTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {
    private Object clickedOn = null;
    private JTextField textField = null;

    public DraftTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer) {
        super(tree, renderer);
    }
    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row) {
        clickedOn = value;
        textField = new JTextField(value.toString());
        textField.addActionListener(this);
        return textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!(clickedOn instanceof DraftTreeItem item)) return;
        item.setName(e.getActionCommand());
    }
}