package raf.draft.dsw.gui.swing;

import raf.draft.dsw.core.GUI;
import raf.draft.dsw.gui.swing.view.MainFrame;

public class SwingGUI implements GUI {
    private MainFrame mainFrameInstance;
    public SwingGUI() {

    }
    @Override
    public void start() {
        mainFrameInstance = MainFrame.getInstance();
        mainFrameInstance.setVisible(true);
    }
}
