package cz.cvut.fel.ondrepe1.ftaeditor.ui.diagram;

import cz.cvut.fel.ondrepe1.ftaeditor.TestDataFactory;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.diagram.model.DiagramTreeTableModel;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.diagram.model.DiagramTreeTableValidityModel;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.diagram.model.icon.DiagramTreeIconStringValue;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.diagram.model.icon.DiagramTreeIconValue;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.diagram.model.icon.DiagramTreeStringValue;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.tree.TreeCellRenderer;
import org.jdesktop.swingx.JXTreeTable;
import org.jdesktop.swingx.renderer.DefaultTreeRenderer;
import org.jdesktop.swingx.renderer.IconValue;

/**
 *
 * @author ondrepe
 */
public class DiagramTreePanel extends JPanel {

    private JScrollPane scrollPane;
    private JXTreeTable table;

    public DiagramTreePanel() {
        initComponents();
    }
    
    private void initComponents() {
        scrollPane = new JScrollPane();
    
//        DiagramTreeTableModel model = new DiagramTreeTableModel();
        DiagramTreeTableValidityModel model = new DiagramTreeTableValidityModel();
        model.setRoot(TestDataFactory.generateFTATree());
        table = new JXTreeTable();
        
        IconValue iv = new DiagramTreeIconValue(new DiagramTreeIconStringValue());
        TreeCellRenderer renderer = new DefaultTreeRenderer(iv, new DiagramTreeStringValue(), false);
        table.setTreeCellRenderer(renderer);
        table.setTreeTableModel(model);
        
        table.packColumn(table.getHierarchicalColumn(), -1);
        table.expandAll();
        scrollPane.setViewportView(table);
        this.add(scrollPane);
    }
}
