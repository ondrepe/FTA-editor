package cz.cvut.fel.ondrepe1.ftaeditor.ui.window;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.symbol.AddSymbolEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.icon.DiagramTreeIconStringValue;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.icon.DiagramTreeIconValue;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.icon.DiagramTreeStringValue;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.window.model.TypeComboBoxModel;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private JButton addButton;
    private JLabel lblError;
    
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
        cmbType.setSelectedIndex(0);
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
        
        lblError = new JLabel();
        lblError.setForeground(Color.red);
        
        addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                lblError.setText("");
                boolean result = true;
                
                AbstractSymbol symbol = (AbstractSymbol) cmbType.getSelectedItem();
                symbol.setLabel(txfLabel.getText());
                symbol.setText(txfText.getText());
                try {
                    String fPText = txfFailureProbability.getText();
                    if (fPText != null && !fPText.isEmpty()) {
                        Float failureProbability = Float.valueOf(fPText);
                        symbol.setFailureProbability(failureProbability);
                    }
                } catch (NumberFormatException ex) {
                    lblError.setText("not a number error");
                    result = false;
                }
                if (result) {
                    FtaController.getInstance().fireEvent(new AddSymbolEvent(symbol, parent));
                    AddSymbolWindow.this.dispose();
                }
            }
        });
        
        mainPanel.add(lblType);
        mainPanel.add(cmbType);
        mainPanel.add(lblLabel);
        mainPanel.add(txfLabel);
        mainPanel.add(lblText);
        mainPanel.add(txfText);
        mainPanel.add(lblFailureProbability);
        mainPanel.add(txfFailureProbability);
        mainPanel.add(addButton);
        mainPanel.add(lblError);
        
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
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, addButton, 0, SpringLayout.HORIZONTAL_CENTER, mainPanel);
        layout.putConstraint(SpringLayout.SOUTH, addButton, -10, SpringLayout.SOUTH, mainPanel);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblError, 0, SpringLayout.HORIZONTAL_CENTER, mainPanel);
        layout.putConstraint(SpringLayout.SOUTH, lblError, -10, SpringLayout.NORTH, addButton);
        
        this.getContentPane().add(mainPanel);
    }
    
    
}
