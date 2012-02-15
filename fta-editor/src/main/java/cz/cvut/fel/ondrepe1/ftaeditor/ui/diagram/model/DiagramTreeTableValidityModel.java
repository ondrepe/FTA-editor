package cz.cvut.fel.ondrepe1.ftaeditor.ui.diagram.model;

import cz.cvut.fel.ondrepe1.ftaeditor.common.image.ImageHolder;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
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
            return "Valid";
        }
        return super.getColumnName(i);
    }
    
    @Override
    public Object getValueAt(Object o, int i) {
        AbstractSymbol symbol = (AbstractSymbol) o;
        if (i == 4) {
            Icon icon = null;
            if (symbol.validate()) { 
                icon = ImageHolder.getTickIcon();
            } else {
                icon = ImageHolder.getCrossIcon();
            }
            return icon;
        }
        return super.getValueAt(o, i);
    }
}
