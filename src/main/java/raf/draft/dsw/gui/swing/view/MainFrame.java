package raf.draft.dsw.gui.swing.view;

import raf.draft.dsw.core.ApplicationFramework;
import raf.draft.dsw.gui.swing.controller.actions.ActionManager;
import raf.draft.dsw.gui.swing.controller.observer.ISubscriber;
import raf.draft.dsw.gui.swing.model.tabs.TabPaneModel;
import raf.draft.dsw.gui.swing.tree.DraftTree;
import raf.draft.dsw.gui.swing.tree.DraftTreeImplementation;
import raf.draft.dsw.gui.swing.model.messages.Message;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MainFrame extends JFrame implements ISubscriber {
    private static MainFrame instance;
    private ActionManager actionManager;
    private DraftTree draftTree;
    private TabPane tabPane;
    private TabPaneModel tabPaneModel;
    private ProjectView projectView;
    private RoomView roomView;
    private void initialize(){
        actionManager = new ActionManager();
        draftTree = new DraftTreeImplementation();
        tabPaneModel = new TabPaneModel();
        projectView = new ProjectView();
        roomView = new RoomView();
        ApplicationFramework.getInstance().getMessageGenerator().addSubscriber(this);
    }
    private void initializeGUI(){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int appWidth = screenSize.width * 3/4;
        int appHeight = screenSize.height * 3/4;
        setSize(appWidth, appHeight);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("DraftRoom");

        MenuBar menu = new MenuBar();
        setJMenuBar(menu);

        ToolBar toolBar = new ToolBar();
        add(toolBar, BorderLayout.NORTH);

        JTree projectExplorer = draftTree.generateTree(ApplicationFramework.getInstance().getDraftRoomRepository().getProjectExplorer());
        JPanel desktop = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(projectExplorer);
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, desktop);
        getContentPane().add(split,BorderLayout.CENTER);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);

        tabPane = new TabPane(tabPaneModel);
        tabPane.setPreferredSize(new Dimension(appWidth - 240, appHeight - 150));
        desktop.add(tabPane, BorderLayout.CENTER);

        JPanel sidePanels = new JPanel();
        sidePanels.setLayout(new BoxLayout(sidePanels, BoxLayout.Y_AXIS));
        sidePanels.setAlignmentX(Component.LEFT_ALIGNMENT);
        sidePanels.add(projectView);
        sidePanels.add(roomView);
        desktop.add(sidePanels,BorderLayout.EAST);
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

    public TabPaneModel getTabPaneModel() {
        return tabPaneModel;
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
