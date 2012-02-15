package cz.cvut.fel.ondrepe1.ftaeditor.listener.menu;

import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.DiagramTreePanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBoxMenuItem;

/**
 *
 * @author ondrejicek
 */
public class ShowDiagramTreeTableValidityListener implements ActionListener{

    DiagramTreePanel panel;
    
    public ShowDiagramTreeTableValidityListener(DiagramTreePanel panel) {
        this.panel = panel;
    }
    
    public void actionPerformed(ActionEvent e) {
        JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) e.getSource();
        panel.showValidation(menuItem.isSelected());
    }
    
}
