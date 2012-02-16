package cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.editor;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.symbol.PalletSymbolSelectEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.symbol.IPalletSymbolSelectListener;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

/**
 *
 * @author ondrejicek
 */
public class EditorPanel extends JPanel implements IPalletSymbolSelectListener {

    private JTabbedPane tabbedPane;
    private AbstractSymbol selectedSymbol;
    
    public EditorPanel() {
        initLayout();
        initComponents();
        registerListeners();
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
    }
    
    public void onPalletSelect(AbstractSymbol symbol) {
        selectedSymbol = symbol;
    }

    private void registerListeners() {
        FtaController.getInstance().registerEventListener(PalletSymbolSelectEvent.class, this);
    }    
}
