package cz.cvut.fel.ondrepe1.ftaeditor.ui.window;

import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.icon.DiagramTreeIconStringValue;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.icon.DiagramTreeIconValue;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.icon.DiagramTreeStringValue;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.window.model.TypeComboBoxModel;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.HeadlessException;
import javax.swing.*;
import org.jdesktop.swingx.JXComboBox;
import org.jdesktop.swingx.renderer.DefaultListRenderer;
import org.jdesktop.swingx.renderer.IconValue;

/**
 *
 * @author ondrepe
 */
public class AddSymbolWindow extends JFrame {

    private AbstractSymbol parent;
    private AbstractSymbol newItem;

    private JPanel mainPanel;
    
    private JLabel lblType;
    private JXComboBox cmbType;
    private JLabel lblLabel;
    private JTextField txfLabel;
    private JLabel lblText;
    private JTextField txfText;
    private JLabel lblFailureProbability;
    private JTextField txfFailureProbability;
    
    public AddSymbolWindow(AbstractSymbol parent) throws HeadlessException {
        super("Add Symbol");
        this.parent = parent;
        
        mainPanel = new JPanel();
        initComponents();
        
        setSize(300, 200);
        setResizable(false);
        setModalExclusionType(Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
    }

    private void initComponents() {
        SpringLayout layout = new SpringLayout();
        mainPanel.setLayout(layout);
        
        lblType = new JLabel("Type: ");
        cmbType = new JXComboBox(new TypeComboBoxModel());
        
        IconValue iv = new DiagramTreeIconValue(new DiagramTreeIconStringValue());
        ListCellRenderer renderer = new DefaultListRenderer(new DiagramTreeStringValue(), iv);
        cmbType.setRenderer(renderer);
        cmbType.setPreferredSize(new Dimension(220, 20));
        
        lblLabel = new JLabel("Label: ");
        txfLabel = new JTextField();
        txfLabel.setPreferredSize(new Dimension(220, 20));
        
        lblText = new JLabel("Text: ");
        txfText = new JTextField();
        txfText.setPreferredSize(new Dimension(220, 20));
        
        lblFailureProbability = new JLabel("Q: ");
        txfFailureProbability = new JTextField();
        txfFailureProbability.setPreferredSize(new Dimension(220, 20));
        
        mainPanel.add(lblType);
        mainPanel.add(cmbType);
        mainPanel.add(lblLabel);
        mainPanel.add(txfLabel);
        mainPanel.add(lblText);
        mainPanel.add(txfText);
        mainPanel.add(lblFailureProbability);
        mainPanel.add(txfFailureProbability);
        
        layout.putConstraint(SpringLayout.EAST, cmbType, -10, SpringLayout.EAST, mainPanel);
        layout.putConstraint(SpringLayout.EAST, lblType, -10, SpringLayout.WEST, cmbType);
        layout.putConstraint(SpringLayout.NORTH, cmbType, 10, SpringLayout.NORTH, mainPanel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, lblType, 0, SpringLayout.VERTICAL_CENTER, cmbType);
        
        layout.putConstraint(SpringLayout.EAST, lblLabel, 0, SpringLayout.EAST, lblType);
        layout.putConstraint(SpringLayout.NORTH, lblLabel, 5, SpringLayout.SOUTH, lblType);
        layout.putConstraint(SpringLayout.WEST, txfLabel, 0, SpringLayout.WEST, cmbType);
        layout.putConstraint(SpringLayout.NORTH, txfLabel, 5, SpringLayout.SOUTH, cmbType);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, lblLabel, 0, SpringLayout.VERTICAL_CENTER, txfLabel);
        
        layout.putConstraint(SpringLayout.EAST, lblText, 0, SpringLayout.EAST, lblLabel);
        layout.putConstraint(SpringLayout.WEST, txfText, 0, SpringLayout.WEST, txfLabel);
        layout.putConstraint(SpringLayout.NORTH, txfText, 5, SpringLayout.SOUTH, txfLabel);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, lblText, 0, SpringLayout.VERTICAL_CENTER, txfText);
        
        layout.putConstraint(SpringLayout.EAST, lblFailureProbability, 0, SpringLayout.EAST, lblText);
        layout.putConstraint(SpringLayout.NORTH, lblFailureProbability, 5, SpringLayout.SOUTH, lblText);
        layout.putConstraint(SpringLayout.WEST, txfFailureProbability, 0, SpringLayout.WEST, txfText);
        layout.putConstraint(SpringLayout.NORTH, txfFailureProbability, 5, SpringLayout.SOUTH, txfText);
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, lblFailureProbability, 0, SpringLayout.VERTICAL_CENTER, txfFailureProbability);
        
        this.getContentPane().add(mainPanel);
    }
    
    
}
