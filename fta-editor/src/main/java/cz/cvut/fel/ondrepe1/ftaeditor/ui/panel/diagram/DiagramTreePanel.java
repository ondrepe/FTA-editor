package cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram;

import cz.cvut.fel.ondrepe1.ftaeditor.common.image.ImageHolder;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.ISymbolSelectListener;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.event.SymbolSelectEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event.AbstractEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate.AbstractGate;
import cz.cvut.fel.ondrepe1.ftaeditor.listener.diagram.DiagramTreeSelectionListener;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.api.IDiagramTreeTableModel;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.icon.DiagramTreeIconStringValue;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.icon.DiagramTreeIconValue;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.icon.DiagramTreeStringValue;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.impl.DiagramTreeTableModel;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.impl.DiagramTreeTableValidityModel;
import java.awt.*;
import javax.swing.*;
import javax.swing.tree.TreeCellRenderer;
import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.renderer.DefaultTreeRenderer;
import org.jdesktop.swingx.renderer.IconValue;

/**
 *
 * @author ondrepe
 */
public class DiagramTreePanel extends JPanel implements ISymbolSelectListener {

    private AbstractSymbol data;
    
    private JButton addSymbolButton;
    private JButton removeSymbolButton;
    private JButton changeTypeButton;
    
    private JLabel infoLabel;
    
    private JScrollPane scrollPane;
    private JXTreeTable table;

    public DiagramTreePanel() {
        initComponents();
        FtaController.getInstance().registerEventListener(SymbolSelectEvent.class, this);
    }

    public AbstractSymbol getData() {
        return data;
    }

    public void setData(AbstractSymbol data) {
        this.data = data;
        IDiagramTreeTableModel model = getModel();
        if (model instanceof DiagramTreeTableModel) {
            model = new DiagramTreeTableModel();
        } else if (model instanceof DiagramTreeTableValidityModel) {
            model = new DiagramTreeTableValidityModel();
        }
        updateModel(model);
    }
    
    private void initComponents() {
        initLayout();
        initButtons();
        initTable();
        
        addComponents();
    }
    
    protected void initLayout() {
        GridBagLayout mainLayout = new GridBagLayout();
        this.setLayout(mainLayout);
    }
    
    protected void initButtons() {
        addSymbolButton = new JButton();
        addSymbolButton.setIcon(ImageHolder.getBtnAddIcon());
        addSymbolButton.setDisabledIcon(ImageHolder.getBtnAddDisabledIcon());
        addSymbolButton.setEnabled(false);
        
        removeSymbolButton = new JButton();
        removeSymbolButton.setIcon(ImageHolder.getBtnRemoveIcon());
        removeSymbolButton.setDisabledIcon(ImageHolder.getBtnRemoveDisabledIcon());
        removeSymbolButton.setEnabled(false);
        
        changeTypeButton = new JButton("Change");
    }
    
    protected void initTable() {
        DiagramTreeTableModel model = new DiagramTreeTableModel(); 
        IconValue iv = new DiagramTreeIconValue(new DiagramTreeIconStringValue());
        TreeCellRenderer renderer = new DefaultTreeRenderer(iv, new DiagramTreeStringValue(), false);
        
        table = new JXTreeTable();
        table.setTreeCellRenderer(renderer);
        table.setTreeTableModel(model);
        
        table.packColumn(table.getHierarchicalColumn(), -1);
        table.setScrollsOnExpand(true);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setPreferredScrollableViewportSize(new Dimension(780, 100));
        
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        scrollPane.setPreferredSize(new Dimension(800, 200));

        table.addTreeSelectionListener(new DiagramTreeSelectionListener());
    }
    
    protected void addComponents() {
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1; c.weighty = 1;
        c.gridx = 0; c.gridy = 1;
        c.gridheight = 1; c.gridwidth = 7;
        c.insets = new Insets(0, 5, 5, 5);
        this.add(scrollPane, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.gridx = 0; c.gridy = 0;
        c.gridheight = 1; c.gridwidth = 1;
        c.insets = new Insets(5, 5, 5, 0);
        this.add(addSymbolButton, c);
        
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.NONE;
        c.gridx = 1; c.gridy = 0;
        c.gridheight = 1; c.gridwidth = 1;
        c.insets = new Insets(5, 5, 5, 0);
        this.add(removeSymbolButton, c);
    }
    
    protected IDiagramTreeTableModel getModel() {
        return (IDiagramTreeTableModel) table.getTreeTableModel();
    }
    
    public void showValidation(boolean show) {
        IDiagramTreeTableModel model = getModel();
        if (show) {
            if (model instanceof DiagramTreeTableModel) {
                IDiagramTreeTableModel newModel = new DiagramTreeTableValidityModel();
                updateModel(newModel);
            }
        } else {
            if (model instanceof DiagramTreeTableValidityModel) {
                IDiagramTreeTableModel newModel = new DiagramTreeTableModel();
                updateModel(newModel);
            }
        }
    }
    
    protected void updateModel(IDiagramTreeTableModel model) {
        model.setData(data);
        table.setTreeTableModel(model);
        table.expandAll();
    }

    public void onSelect(AbstractSymbol symbol) {
        if(symbol instanceof AbstractEvent) {
            disableAddButton();
            enableRemoveButton();
        } else {
            AbstractGate gate = (AbstractGate) symbol;
            if (gate.canAddChild()) {
                enableAddButton();
            } else {
                disableAddButton();
            }
            if (gate.getChildren().isEmpty()) {
                enableRemoveButton();
            } else {
                disableRemoveButton();
            }
        }
    }
    
    protected void disableAddButton() {
        addSymbolButton.setEnabled(false);
    }
    
    protected void enableAddButton() {
        addSymbolButton.setEnabled(true);
    }

    protected void disableRemoveButton() {
        removeSymbolButton.setEnabled(false);
    }
    
    protected void enableRemoveButton() {
        removeSymbolButton.setEnabled(true);
    }
}
