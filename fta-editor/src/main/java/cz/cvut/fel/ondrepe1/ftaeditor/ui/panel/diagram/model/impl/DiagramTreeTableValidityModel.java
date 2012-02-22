package cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.impl;

import cz.cvut.fel.ondrepe1.ftaeditor.common.image.ImageHolder;
import cz.cvut.fel.ondrepe1.ftaeditor.data.IDataItem;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author ondrepe
 */
public class DiagramTreeTableValidityModel extends DiagramTreeTableModel {

    @Override
    public Class<?> getColumnClass(int i) {
        if(i == 4) return ImageIcon.class;
        return super.getColumnClass(i);
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int i) {
        if (i == 4) {
            return "Validita";
        }
        return super.getColumnName(i);
    }
    
    @Override
    public Object getValueAt(Object o, int i) {
        IDataItem symbol = (IDataItem) o;
        if (i == 4) {
            Icon icon;
            if (symbol.isValid()) { 
                icon = ImageHolder.getTickIcon();
            } else {
                icon = ImageHolder.getCrossIcon();
            }
            return icon;
        }
        return super.getValueAt(o, i);
    }
}
