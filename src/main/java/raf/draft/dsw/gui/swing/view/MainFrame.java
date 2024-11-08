package raf.draft.dsw.gui.swing.view;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.controller.ActionManager;
import raf.draft.dsw.gui.swing.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.tree.DraftTree;
import raf.draft.dsw.gui.swing.tree.DraftTreeImplementation;
import raf.draft.dsw.gui.swing.model.messages.Message;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame implements ISubscriber {
    private static MainFrame instance;
    private ActionManager actionManager;
    private DraftTree draftTree;
    private TabPane tabPane;
    private void initialize(){
        actionManager = new ActionManager();
        draftTree = new DraftTreeImplementation();
        ApplicationFramework.getInstance().getMessageGenerator().addSubscriber(this);
    }
    private void initializeGUI(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("DraftRoom");

        MenuBar menu = new MenuBar();
        setJMenuBar(menu);

        ToolBar toolBar = new ToolBar();
        add(toolBar, BorderLayout.NORTH);

        JTree projectExplorer = draftTree.generateTree(ApplicationFramework.getInstance().getDraftRoomRepository().getProjectExplorer());
        JPanel desktop = new JPanel();
        JScrollPane scroll = new JScrollPane(projectExplorer);
        scroll.setMinimumSize(new Dimension(180,150));
        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,scroll,desktop);
        getContentPane().add(split,BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);

        tabPane = new TabPane();
        tabPane.setPreferredSize(new Dimension(screenWidth / 2 - 240, screenHeight / 2 - 150));
        desktop.add(tabPane);
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public DraftTree getDraftTree() {
        return draftTree;
    }

    public TabPane getTabPane() {
        return tabPane;
    }

    @Override
    public void update(Object value) {
        if(value instanceof Message message) displayEventMessage(message);
    }

    private void displayEventMessage(Message message) {
        String messageType = message.getType().toString();
        String messageText = message.toString();

        int messageTypeJOptionPane = switch (message.getType()) {
            case ERROR -> JOptionPane.ERROR_MESSAGE;
            case WARNING -> JOptionPane.WARNING_MESSAGE;
            case INFO -> JOptionPane.INFORMATION_MESSAGE;
        };

        JOptionPane.showMessageDialog(this, messageText, messageType, messageTypeJOptionPane, null);
    }

    private MainFrame() {
    }
    public static MainFrame getInstance() {
        if (instance == null){
            instance = new MainFrame();
            instance.initialize();
            instance.initializeGUI();
        }
        return instance;
    }
}
