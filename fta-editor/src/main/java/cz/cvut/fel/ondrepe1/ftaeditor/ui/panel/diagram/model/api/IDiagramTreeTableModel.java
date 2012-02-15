/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.api;

import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import org.jdesktop.swingx.treetable.TreeTableModel;

/**
 *
 * @author ondrejicek
 */
public interface IDiagramTreeTableModel extends TreeTableModel {
    
    public AbstractSymbol getData();
    public void setData(AbstractSymbol data);
}
