package cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.editor;

import cz.cvut.fel.ondrepe1.ftaeditor.TestDataFactory;
import cz.cvut.fel.ondrepe1.ftaeditor.common.image.ImageHolder;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.DataChangedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.EditorDataChangedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.editor.EditorToolbarButtonChangeEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.IEditorDataChangedListener;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.editor.IEditorToolbarButtonChangeListener;
import cz.cvut.fel.ondrepe1.ftaeditor.listener.editor.EditorToolBarButtonItemListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import org.apache.batik.util.gui.resource.JToolbarToggleButton;

/**
 *
 * @author ondrejicek
 */
public class EditorPanel extends JPanel implements IEditorDataChangedListener, IEditorToolbarButtonChangeListener {

    private JTabbedPane tabbedPane;
    
    private EditorCanvas canvas;
    
    private JToolBar toolbar;
    private JToolbarToggleButton btnBasicEvent;
    private JToolbarToggleButton btnConditionalEvent;
    private JToolbarToggleButton btnDormantEvent;
    private JToolbarToggleButton btnUndevelopedEvent;
    private JToolbarToggleButton btnAndGate;
    private JToolbarToggleButton btnOrGate;
    private JToolbarToggleButton btnXorGate;
    private JToolbarToggleButton btnInhibitGate;
    private JToolbarToggleButton btnMajorityVoteGate;
    private JToolbarToggleButton btnNotGate;
    private JToolbarToggleButton btnPandGate;
    private JToolbarToggleButton btnTransfer;
    
    public EditorPanel() {
        initLayout();
        initComponents();
        registerListeners();
        loadData();
    }
    
    private void initLayout() {
        GridBagLayout mainLayout = new GridBagLayout();
        this.setLayout(mainLayout);
    }
    
    private JToolbarToggleButton createButton(String iconPath) {
        JToolbarToggleButton button = new JToolbarToggleButton();
        button.setIcon(ImageHolder.loadFromSvgResource(iconPath, true));
        button.addItemListener(new EditorToolBarButtonItemListener());
        
        return button;
    }
    
    private void initToolbar() {
        toolbar = new JToolBar(JToolBar.VERTICAL);
        toolbar.setToolTipText("Pallet");
        toolbar.setFloatable(false);
        toolbar.setSize(20, 100);
        
        btnBasicEvent = createButton("event/basic.svg");
        btnConditionalEvent = createButton("event/conditional.svg");
        btnDormantEvent = createButton("event/dormant.svg");
        btnUndevelopedEvent = createButton("event/undeveloped.svg");
        btnAndGate = createButton("gate/and.svg");
        btnOrGate = createButton("gate/or.svg");
        btnXorGate = createButton("gate/xor.svg");
        btnInhibitGate = createButton("gate/inhibit.svg");
        btnMajorityVoteGate = createButton("gate/mojorityVote.svg");
        btnNotGate = createButton("gate/not.svg");
        btnPandGate = createButton("gate/pand.svg");
        btnTransfer = createButton("gate/transfer.svg");
        
        toolbar.add(btnBasicEvent);
        toolbar.add(btnConditionalEvent);
        toolbar.add(btnDormantEvent);
        toolbar.add(btnUndevelopedEvent);
        toolbar.addSeparator();
        toolbar.add(btnAndGate);
        toolbar.add(btnOrGate);
        toolbar.add(btnNotGate);
        toolbar.add(btnXorGate);
        toolbar.add(btnMajorityVoteGate);
        toolbar.add(btnInhibitGate);
        toolbar.add(btnPandGate);
        toolbar.add(btnTransfer);
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        this.add(toolbar,c);
    }
    
    private void initComponents() {
        initToolbar();

        tabbedPane = new JTabbedPane();
        JScrollPane scrollPane = new JScrollPane();
//        eventsPane.setViewportView(eventsList);
        
        tabbedPane.addTab("New FTA" ,scrollPane);
        //canvas.getBounds();
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0, 5, 5, 5);
        this.add(tabbedPane, c);
        
        
        canvas = new EditorCanvas();
        scrollPane.setViewportView(canvas);
    }
    
    private void loadData() {
        FtaController.getInstance().fireEvent(new DataChangedEvent(TestDataFactory.generateFTATree()));
    }

    private void registerListeners() {
        FtaController.getInstance().registerEventListener(EditorToolbarButtonChangeEvent.class, this);
    }

    public void onEvent(EditorToolbarButtonChangeEvent event) {
        if(event.isSelected()) {
            JToolbarToggleButton btn = event.getButton();
            if (btn != btnAndGate) {
                btnAndGate.setSelected(false);
            }
            if (btn != btnBasicEvent) {
                btnBasicEvent.setSelected(false);
            } 
            if (btn != btnConditionalEvent) {
                btnConditionalEvent.setSelected(false);
            }
            if (btn != btnDormantEvent) {
                btnDormantEvent.setSelected(false);
            }
            if (btn != btnInhibitGate) {
                btnInhibitGate.setSelected(false);
            }
            if (btn != btnMajorityVoteGate) {
                btnMajorityVoteGate.setSelected(false);
            }
            if (btn != btnNotGate) {
                btnNotGate.setSelected(false);
            }
            if (btn != btnOrGate) {
                btnOrGate.setSelected(false);
            }
            if (btn != btnPandGate) {
                btnPandGate.setSelected(false);
            }
            if (btn != btnTransfer) {
                btnTransfer.setSelected(false);
            }
            if (btn != btnUndevelopedEvent) {
                btnUndevelopedEvent.setSelected(false);
            }
            if (btn != btnXorGate) {
                btnXorGate.setSelected(false);
            }
        }
    }

    public void onEvent(EditorDataChangedEvent event) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }
}
