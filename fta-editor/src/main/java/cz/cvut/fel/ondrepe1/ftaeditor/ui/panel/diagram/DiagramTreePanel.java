package cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram;

import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.api.IDiagramTreeTableModel;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.icon.DiagramTreeIconStringValue;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.icon.DiagramTreeIconValue;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.icon.DiagramTreeStringValue;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.impl.DiagramTreeTableModel;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.impl.DiagramTreeTableValidityModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.*;
import javax.swing.tree.TreeCellRenderer;
import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.renderer.DefaultTreeRenderer;
import org.jdesktop.swingx.renderer.IconValue;

/**
 *
 * @author ondrepe
 */
public class DiagramTreePanel extends JPanel {

    private AbstractSymbol data;
    
    private JPanel mainPanel = new JPanel();
    
    private JButton addSymbolButton;
    private JButton removeSymbolButton;
    private JButton changeTypeButton;
    
    private JLabel infoLabel;
    
    private JScrollPane scrollPane;
    private JXTreeTable table;

    public DiagramTreePanel() {
        initComponents();
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
        mainPanel = new JPanel();
        mainPanel.setBackground(Color.red);
        setBackground(Color.green);
        initTable();
//        this.add(mainPanel);
        GridBagLayout mainLayout = new GridBagLayout();
//        mainPanel.setLayout(mainLayout);
        this.setLayout(mainLayout);
        
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight = 1;
        c.gridwidth = 7;
        
        this.add(scrollPane, c);
        
//        this.setLayout(mainLayout);
//        mainLayout.putConstraint(SpringLayout.WEST, scrollPane, 5, SpringLayout.WEST, mainPanel);
    }
    
    protected void initButtons() {
        addSymbolButton = new JButton("Add");
        removeSymbolButton = new JButton("Remove");
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
//        mainPanel.add(scrollPane);
        //this.add(scrollPane);
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
}
