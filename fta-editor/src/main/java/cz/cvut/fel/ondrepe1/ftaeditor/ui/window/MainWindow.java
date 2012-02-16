package cz.cvut.fel.ondrepe1.ftaeditor.ui.window;

import cz.cvut.fel.ondrepe1.ftaeditor.FtaEditor;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.IOpenAddSymbolWindowListener;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.OpenAddSymbolWindowEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import cz.cvut.fel.ondrepe1.ftaeditor.listener.WindowClosingListener;
import cz.cvut.fel.ondrepe1.ftaeditor.listener.menu.ExitListener;
import cz.cvut.fel.ondrepe1.ftaeditor.listener.menu.ShowDiagramTreeTableValidityListener;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.DiagramTreePanel;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.editor.EditorPanel;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.pallet.PalletPanel;
import java.awt.*;
import javax.swing.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ondrepe
 */
public class MainWindow extends JFrame implements IOpenAddSymbolWindowListener {

    public static final Logger logger = LoggerFactory.getLogger(MainWindow.class);
    
    public static final String MAIN_WINDOW_TITLE = "FTA editor";
    
    private JPanel mainPanel;
    
    private JMenuBar menuBar;
    
    private JMenu file;
    private JMenuItem exit;
    
    private JMenu view;
    private JMenuItem showPalletWindow;
    private JCheckBoxMenuItem showDiagramTreeValidity;
    
    private JMenu help;
    private JMenuItem about;
    
    private PalletPanel palletPanel;
    private DiagramTreePanel diagramTreePanel;
    private EditorPanel editorPanel;
    
    public MainWindow() throws HeadlessException {
        super(MAIN_WINDOW_TITLE);
        this.setSize(1000, 1000);
        createPanelInstances();
        initComponents();
        initListeners();
        
//        diagramTreePanel.setData(editor.getController().getData());
        
        setVisible(true);
        registerListeners();
    }
    
    protected final void createPanelInstances() {
        mainPanel = new JPanel();
        
        palletPanel = new PalletPanel();
        editorPanel = new EditorPanel();
        diagramTreePanel = new DiagramTreePanel();
    }
    
    protected final void initComponents() {
        initMenu();
        this.setJMenuBar(menuBar);
        
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 10;
        c.gridwidth = 10;
        
        mainPanel.setBackground(Color.blue);
        
        mainPanel.setLayout(layout);
        mainPanel.add(diagramTreePanel, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 2;
        c.weighty = 8;
        c.gridx = 0;
        c.gridy = 10;
        c.gridheight = 40;
        c.gridwidth = 2;
        mainPanel.add(palletPanel, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 8;
        c.weighty = 8;
        c.gridx = 2;
        c.gridy = 10;
        c.gridheight = 40;
        c.gridwidth = 8;
        mainPanel.add(editorPanel, c);

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
        exit = new JMenuItem("Exit");
        file.add(exit);
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
        exit.addActionListener(new ExitListener());
        showDiagramTreeValidity.addActionListener(new ShowDiagramTreeTableValidityListener(diagramTreePanel));
    }
    
    private void registerListeners() {
        FtaController.getInstance().registerEventListener(OpenAddSymbolWindowEvent.class, this);
    }

    public void onOpenAddSymbolWindow(AbstractSymbol parent) {
        AddSymbolWindow addWindow = new AddSymbolWindow(parent);
        addWindow.setVisible(true);
    }
}
