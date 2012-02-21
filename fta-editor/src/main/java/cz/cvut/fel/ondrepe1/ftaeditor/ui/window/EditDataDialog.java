package cz.cvut.fel.ondrepe1.ftaeditor.ui.window;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataEditItemEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaDataItem;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author ondrepe
 */
public class EditDataDialog extends JDialog {

    private FtaDataItem dataItem;

    private JPanel mainPanel;
    
    private JLabel lblLabel;
    private JTextField txfLabel;
    private JLabel lblText;
    private JTextField txfText;
    private JLabel lblFailureProbability;
    private JTextField txfFailureProbability;
    private JButton editButton;
    private JLabel lblError;
    
    public EditDataDialog(FtaDataItem dataItem, Window owner) {
        super(owner, "Edit Item", ModalityType.APPLICATION_MODAL);
        this.dataItem = dataItem;
        
        setSize(300, 150);
        setResizable(false);
        
        mainPanel = new JPanel();
        initComponents();
    }

    private void initComponents() {
        SpringLayout layout = new SpringLayout();
        mainPanel.setLayout(layout);
        
        lblLabel = new JLabel("Label: ");
        txfLabel = new JTextField();
        txfLabel.setText(dataItem.getLabel());
        txfLabel.setPreferredSize(new Dimension(220, 20));
        
        lblText = new JLabel("Text: ");
        txfText = new JTextField();
        txfText.setText(dataItem.getText());
        txfText.setPreferredSize(new Dimension(220, 20));
        
        lblFailureProbability = new JLabel("Q: ");
        txfFailureProbability = new JTextField();
        if (dataItem.getFailureProbability() != null) {
            txfFailureProbability.setText(String.valueOf(dataItem.getFailureProbability()));
        }
        txfFailureProbability.setPreferredSize(new Dimension(220, 20));
        
        lblError = new JLabel();
        lblError.setForeground(Color.red);
        
        editButton = new JButton("Edit");
        editButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                lblError.setText("");
                boolean result = true;
                
                dataItem.setLabel(txfLabel.getText());
                dataItem.setText(txfText.getText());
                try {
                    String fPText = txfFailureProbability.getText();
                    if (fPText != null && !fPText.isEmpty()) {
                        Float failureProbability = Float.valueOf(fPText);
                        dataItem.setFailureProbability(failureProbability);
                    }
                } catch (NumberFormatException ex) {
                    lblError.setText("not a number error");
                    result = false;
                }
                if (result) {
                    FtaController.getInstance().fireEvent(new DataEditItemEvent(dataItem));
                    EditDataDialog.this.dispose();
                }
            }
        });
        
        mainPanel.add(lblLabel);
        mainPanel.add(txfLabel);
        mainPanel.add(lblText);
        mainPanel.add(txfText);
        mainPanel.add(lblFailureProbability);
        mainPanel.add(txfFailureProbability);
        mainPanel.add(editButton);
        mainPanel.add(lblError);
        
        layout.putConstraint(SpringLayout.EAST, txfLabel, -10, SpringLayout.EAST, mainPanel);
        layout.putConstraint(SpringLayout.EAST, lblLabel, -10, SpringLayout.WEST, txfLabel);
        layout.putConstraint(SpringLayout.NORTH, txfLabel, 10, SpringLayout.NORTH, mainPanel);
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
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, editButton, 0, SpringLayout.HORIZONTAL_CENTER, mainPanel);
        layout.putConstraint(SpringLayout.SOUTH, editButton, -10, SpringLayout.SOUTH, mainPanel);
        
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, lblError, 0, SpringLayout.HORIZONTAL_CENTER, mainPanel);
        layout.putConstraint(SpringLayout.SOUTH, lblError, -10, SpringLayout.NORTH, editButton);
        
        this.getContentPane().add(mainPanel);
    }
}
