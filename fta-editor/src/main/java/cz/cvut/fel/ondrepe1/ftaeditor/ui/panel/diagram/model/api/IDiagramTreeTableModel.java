package cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.api;

import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaData;
import org.jdesktop.swingx.treetable.TreeTableModel;

/**
 *
 * @author ondrejicek
 */
public interface IDiagramTreeTableModel extends TreeTableModel {
    
    public FtaData getData();
    public void setData(FtaData data);
}
