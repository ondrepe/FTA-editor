package cz.cvut.fel.ondrepe1.ftaeditor.ui.window;

import cz.cvut.fel.ondrepe1.ftaeditor.listener.WindowClosingListener;
import cz.cvut.fel.ondrepe1.ftaeditor.listener.menu.ExitListener;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.diagram.DiagramTreePanel;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.pallet.PalletPanel;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import javax.swing.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ondrepe
 */
public class MainWindow extends JFrame {

    public static final Logger logger = LoggerFactory.getLogger(MainWindow.class);
    
    public static final String MAIN_WINDOW_TITLE = "FTA editor";
    
    private JPanel mainPanel;
    
    private JMenuBar menuBar;
    
    private JMenu file;
    private JMenuItem exit;
    
    private JMenu view;
    private JMenuItem showPalletWindow;
    private JMenuItem showDiagramTreeWindow;
    
    private JMenu help;
    private JMenuItem about;
    
    private PalletPanel palletPanel;
    
    public MainWindow() throws HeadlessException {
        super(MAIN_WINDOW_TITLE);
        initComponents();
        initListeners();
        this.setSize(1000, 400);
        setVisible(true);
    }
    
    protected final void initComponents() {
        mainPanel = new JPanel(new BorderLayout());
        palletPanel = new PalletPanel();
        initMenu();
        this.getContentPane().add(mainPanel);
    }
    
    protected final void initMenu() {
        menuBar = new JMenuBar();
        
        initFileMenu();
        initViewMenu();
        initHelpMenu();
        
        mainPanel.add(BorderLayout.NORTH, menuBar);
        //mainPanel.add(BorderLayout.CENTER, new PalletItem(new BasicEvent()));
        mainPanel.add(BorderLayout.CENTER, new DiagramTreePanel());
        //mainPanel.add(BorderLayout.WEST, palletPanel);
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
        showDiagramTreeWindow = new JMenuItem("Show Diagram Tree");
        view.add(showPalletWindow);
        view.add(showDiagramTreeWindow);
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
    }
}
