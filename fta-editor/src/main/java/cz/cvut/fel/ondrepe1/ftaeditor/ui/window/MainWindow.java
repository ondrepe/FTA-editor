package cz.cvut.fel.ondrepe1.ftaeditor.ui.window;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.OpenEditDialogEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.IOpenEditDialogListener;
import cz.cvut.fel.ondrepe1.ftaeditor.listener.WindowClosingListener;
import cz.cvut.fel.ondrepe1.ftaeditor.listener.menu.ExitListener;
import cz.cvut.fel.ondrepe1.ftaeditor.listener.menu.OpenDataActionListener;
import cz.cvut.fel.ondrepe1.ftaeditor.listener.menu.SaveDataActionListener;
import cz.cvut.fel.ondrepe1.ftaeditor.listener.menu.ShowDiagramTreeTableValidityListener;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.DiagramTreePanel;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.editor.EditorPanel;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.window.filechooser.FtaFileChooserDialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.HeadlessException;
import javax.swing.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ondrepe
 */
public class MainWindow extends JFrame implements IOpenEditDialogListener {

    public static final Logger logger = LoggerFactory.getLogger(MainWindow.class);
    
    public static final String MAIN_WINDOW_TITLE = "FTA editor";
    
    private JPanel mainPanel;
    
    private JMenuBar menuBar;
    
    private JMenu file;
    private JMenuItem miNew;
    private JMenuItem miOpen;
    private JMenuItem miSave;
    private JMenuItem miExit;
    
    private JMenu view;
    private JMenuItem showPalletWindow;
    private JCheckBoxMenuItem showDiagramTreeValidity;
    
    private JMenu help;
    private JMenuItem about;
    
    private DiagramTreePanel diagramTreePanel;
    private EditorPanel editorPanel;
    
    public MainWindow() throws HeadlessException {
        super(MAIN_WINDOW_TITLE);
        this.setSize(1000, 1000);
        createPanelInstances();
        initComponents();
        initListeners();
        
        setVisible(true);
        registerListeners();
    }
    
    protected final void createPanelInstances() {
        mainPanel = new JPanel();
        
        editorPanel = new EditorPanel();
        diagramTreePanel = new DiagramTreePanel();
    }
    
    protected final void initComponents() {
        initMenu();
        this.setJMenuBar(menuBar);
        
        GridBagLayout layout = new GridBagLayout();
        mainPanel.setLayout(layout);
        
        JSplitPane verticalSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, diagramTreePanel, editorPanel);
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        mainPanel.add(verticalSplitPane, c);
        
        this.getContentPane().add(mainPanel);
    }
    
    protected final void initMenu() {
        menuBar = new JMenuBar();
        
        initFileMenu();
        initViewMenu();
        initHelpMenu();
    }
    
    private void initFileMenu() {
        file = new JMenu("File");
        miNew = new JMenuItem("New");
        miOpen = new JMenuItem("Open");
        miSave = new JMenuItem("Save");
        miExit = new JMenuItem("Exit");
        file.add(miNew);
        file.add(miOpen);
        file.add(miSave);
        file.add(new JSeparator(JSeparator.HORIZONTAL));
        file.add(miExit);
        menuBar.add(file);
    }
    
    private void initViewMenu() {
        view = new JMenu("View");
        showPalletWindow = new JMenuItem("Show Pallet");
        showDiagramTreeValidity = new JCheckBoxMenuItem("Show validation in Diagram Tree");
        view.add(showPalletWindow);
        view.add(showDiagramTreeValidity);
        menuBar.add(view);
    }
    
    private void initHelpMenu() {
        help = new JMenu("Help");
        about = new JMenuItem("About");
        help.add(about);
        menuBar.add(help);
    }

    private void initListeners() {
        this.addWindowListener(new WindowClosingListener());
        miExit.addActionListener(new ExitListener());
        miNew.addActionListener(null);
        miOpen.addActionListener(new OpenDataActionListener(this, new FtaFileChooserDialog(true)));
        miSave.addActionListener(new SaveDataActionListener(this, new FtaFileChooserDialog(false)));
        showDiagramTreeValidity.addActionListener(new ShowDiagramTreeTableValidityListener(diagramTreePanel));
    }
    
    private void registerListeners() {
        FtaController.getInstance().registerEventListener(OpenEditDialogEvent.class, this);
    }

    public void onEvent(OpenEditDialogEvent event) {
        EditDataDialog dialog = new EditDataDialog(event.getDataItem(), this);
        dialog.setVisible(true);
    }
}
