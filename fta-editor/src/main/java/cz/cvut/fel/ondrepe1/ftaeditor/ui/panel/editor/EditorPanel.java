package cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.editor;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.editor.EditorSvgController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.IEditorDataChangedListener;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.EditorDataChangedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.symbol.PalletSymbolSelectEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.symbol.IPalletSymbolSelectListener;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import org.apache.batik.swing.JSVGCanvas;
import org.w3c.dom.Document;

/**
 *
 * @author ondrejicek
 */
public class EditorPanel extends JPanel implements IPalletSymbolSelectListener, IEditorDataChangedListener {

    private JTabbedPane tabbedPane;
    private AbstractSymbol selectedSymbol;
    
    private JSVGCanvas canvas;
    
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
    
    private void initComponents() {
        tabbedPane = new JTabbedPane();
        JScrollPane scrollPane = new JScrollPane();
//        eventsPane.setViewportView(eventsList);
        
        tabbedPane.addTab("New FTA" ,scrollPane);
        
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 1;
        c.insets = new Insets(0, 5, 5, 5);
        this.add(tabbedPane, c);
        
        canvas = new JSVGCanvas();
        scrollPane.setViewportView(canvas);
    }
    
    private void loadData() {
        canvas.setDocument(EditorSvgController.getInstance().test());
    }
    
    public void onPalletSelect(AbstractSymbol symbol) {
        selectedSymbol = symbol;
    }
    
    public void onEditorDataChange(Document document) {
        canvas.setDocument(document);
    }

    private void registerListeners() {
        FtaController.getInstance().registerEventListener(PalletSymbolSelectEvent.class, this);
        FtaController.getInstance().registerEventListener(EditorDataChangedEvent.class, this);
    }
}
