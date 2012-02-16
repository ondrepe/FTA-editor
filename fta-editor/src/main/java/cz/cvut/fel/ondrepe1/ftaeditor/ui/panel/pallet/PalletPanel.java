package cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.pallet;

import cz.cvut.fel.ondrepe1.ftaeditor.listener.pallet.PalletSelectionListener;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.icon.DiagramTreeIconStringValue;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.icon.DiagramTreeIconValue;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.icon.DiagramTreeStringValue;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.pallet.model.PalletListModel;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.pallet.model.icon.PalletIconValue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;
import org.jdesktop.swingx.JXList;
import org.jdesktop.swingx.renderer.DefaultListRenderer;
import org.jdesktop.swingx.renderer.IconValue;

/**
 *
 * @author ondrepe
 */
public class PalletPanel extends JPanel {
    
    private JTabbedPane tabbedPane;
    private JXList eventsList;
    private JXList gatesList;
    
    public PalletPanel() {
        initLayout();
        initLists();
        initComponents();
    }
    
    private void initLayout() {
        GridBagLayout mainLayout = new GridBagLayout();
        this.setLayout(mainLayout);
    }
    
    private void initLists() {
        IconValue iv = new PalletIconValue(new DiagramTreeIconStringValue());
        ListCellRenderer renderer = new DefaultListRenderer(new DiagramTreeStringValue(), iv);
        
        PalletSelectionListener selectionListener = new PalletSelectionListener();
        
        eventsList = new JXList();
        PalletListModel model = new PalletListModel(PalletPanelDataFactory.getInstance().getEvents());
        eventsList.setModel(model);
        eventsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        eventsList.setCellRenderer(renderer);
        eventsList.addListSelectionListener(selectionListener);
        
        gatesList = new JXList();
        model = new PalletListModel(PalletPanelDataFactory.getInstance().getGates());
        gatesList.setModel(model);
        gatesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        gatesList.setCellRenderer(renderer);
        gatesList.addListSelectionListener(selectionListener);
    }
    
    private void initComponents() {
    
        tabbedPane = new JTabbedPane();
        JScrollPane eventsPane = new JScrollPane();
        eventsPane.setViewportView(eventsList);
        tabbedPane.addTab("Events", eventsPane);
        
        JScrollPane gatesPane = new JScrollPane();
        gatesPane.setViewportView(gatesList);
        tabbedPane.addTab("Gates", gatesPane);
        
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

    
}
