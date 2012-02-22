package cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.editor;

import cz.cvut.fel.ondrepe1.ftaeditor.TestDataFactory;
import cz.cvut.fel.ondrepe1.ftaeditor.common.image.ImageHolder;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaDataController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaEditorController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataChangedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.editor.EditorToolbarButtonChangeEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.editor.IEditorToolbarButtonChangeListener;
import cz.cvut.fel.ondrepe1.ftaeditor.listener.editor.EditorToolBarButtonItemListener;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.editor.canvas.EditorCanvas;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.editor.toolbar.EditorToolbarToggleButton;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

/**
 *
 * @author ondrejicek
 */
public class EditorPanel extends JPanel implements IEditorToolbarButtonChangeListener {

    private EditorTabbedPanel tabbedPane;
    
    private JToolBar toolbar;
    private EditorToolbarToggleButton btnSelect;
    private EditorToolbarToggleButton btnEdit;
    private EditorToolbarToggleButton btnConnect;
    
    private EditorToolbarToggleButton btnBasicEvent;
    private EditorToolbarToggleButton btnConditionalEvent;
    private EditorToolbarToggleButton btnDormantEvent;
    private EditorToolbarToggleButton btnUndevelopedEvent;
    private EditorToolbarToggleButton btnAndGate;
    private EditorToolbarToggleButton btnOrGate;
    private EditorToolbarToggleButton btnXorGate;
    private EditorToolbarToggleButton btnInhibitGate;
    private EditorToolbarToggleButton btnMajorityVoteGate;
    private EditorToolbarToggleButton btnNotGate;
    private EditorToolbarToggleButton btnPandGate;
    private EditorToolbarToggleButton btnTransfer;
    
    private List<EditorToolbarToggleButton> toolbarBtnList;
    
    public EditorPanel() {
        toolbarBtnList = new ArrayList<EditorToolbarToggleButton>();
        
        initLayout();
        initComponents();
        registerListeners();
        loadData();
    }
    
    private void initLayout() {
        GridBagLayout mainLayout = new GridBagLayout();
        this.setLayout(mainLayout);
    }
    
    private EditorToolbarToggleButton createButton(int editorState, String iconPath) {
        EditorToolbarToggleButton button = new EditorToolbarToggleButton(editorState);
        button.setIcon(ImageHolder.loadFromSvgResource(iconPath, true));
        button.addItemListener(new EditorToolBarButtonItemListener());
        toolbarBtnList.add(button);
        
        return button;
    }
    
    private EditorToolbarToggleButton createButton(int editorState) {
        EditorToolbarToggleButton button = new EditorToolbarToggleButton(editorState);
        button.addItemListener(new EditorToolBarButtonItemListener());
        toolbarBtnList.add(button);
        
        return button;
    }
    
    private void initToolbar() {
        toolbar = new JToolBar(JToolBar.VERTICAL);
        toolbar.setToolTipText("Pallet");
        toolbar.setFloatable(false);
        toolbar.setSize(20, 100);
        
        btnSelect = createButton(FtaEditorController.EDITOR_STATE_SELECT);
        btnSelect.setText("s");
        
        btnEdit = createButton(FtaEditorController.EDITOR_STATE_EDIT);
        btnEdit.setText("e");
        
        btnConnect = createButton(FtaEditorController.EDITOR_STATE_CONNECT);
        btnConnect.setText("c");
        
        btnBasicEvent = createButton(FtaEditorController.EDITOR_STATE_BASIC_EVENT, "event/basic.svg");
        btnConditionalEvent = createButton(FtaEditorController.EDITOR_STATE_CONDITIONAL_EVENT, "event/conditional.svg");
        btnDormantEvent = createButton(FtaEditorController.EDITOR_STATE_DORMANT_EVENT, "event/dormant.svg");
        btnUndevelopedEvent = createButton(FtaEditorController.EDITOR_STATE_UNDEVELOPED_EVENT, "event/undeveloped.svg");
        btnAndGate = createButton(FtaEditorController.EDITOR_STATE_AND_GATE, "gate/and.svg");
        btnOrGate = createButton(FtaEditorController.EDITOR_STATE_OR_GATE, "gate/or.svg");
        btnXorGate = createButton(FtaEditorController.EDITOR_STATE_XOR_GATE, "gate/xor.svg");
        btnInhibitGate = createButton(FtaEditorController.EDITOR_STATE_INHIBIT_GATE, "gate/inhibit.svg");
        btnMajorityVoteGate = createButton(FtaEditorController.EDITOR_STATE_MAJORITY_VOTE_GATE, "gate/majorityVote.svg");
        btnNotGate = createButton(FtaEditorController.EDITOR_STATE_NOT_GATE, "gate/not.svg");
        btnPandGate = createButton(FtaEditorController.EDITOR_STATE_PAND_GATE, "gate/pand.svg");
        btnTransfer = createButton(FtaEditorController.EDITOR_STATE_TRANSFER_GATE, "gate/transfer.svg");
        
        toolbar.add(btnSelect);
        toolbar.add(btnEdit);
        toolbar.add(btnConnect);
        toolbar.addSeparator();
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
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        this.add(toolbar,c);
    }
    
    private void initComponents() {
        initToolbar();

        tabbedPane = new EditorTabbedPanel();
        
        tabbedPane.addItem(new EditorTabbedPanelItem(tabbedPane));
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 1;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 8;
        c.insets = new Insets(0, 5, 5, 5);
        this.add(tabbedPane, c);
    }
    
    private void loadData() {
        FtaController.getInstance().fireEvent(new DataChangedEvent(TestDataFactory.generateFTATree()));
    }

    private void registerListeners() {
        FtaController.getInstance().registerEventListener(EditorToolbarButtonChangeEvent.class, this);
    }

    public void onEvent(EditorToolbarButtonChangeEvent event) {
        int editorState = FtaEditorController.EDITOR_STATE_NONE;
        if(event.isSelected()) {
            for (EditorToolbarToggleButton btn : toolbarBtnList) {
                if(event.getButton() == btn) {
                    editorState = btn.getEditorState();
                } else {
                    btn.setSelected(false);
                }
            }
        }
        FtaEditorController.getInstance().setEditorState(editorState);
        FtaDataController.getInstance().resetParent();
    }
}
