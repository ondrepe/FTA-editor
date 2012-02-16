package cz.cvut.fel.ondrepe1.ftaeditor.ui.window.model;

import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import java.util.List;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author ondrepe
 */
public class TypeComboBoxModel implements ComboBoxModel<AbstractSymbol> {

    private List<AbstractSymbol> list;
    private AbstractSymbol selectedItem;
    
    public TypeComboBoxModel() {
        list = TypeComboBoxDataFactory.getInstance().getSymbols();
    }
    
    public void setSelectedItem(Object anItem) {
        this.selectedItem = (AbstractSymbol) anItem;
    }

    public Object getSelectedItem() {
        return selectedItem;
    }

    public int getSize() {
        return list.size();
    }

    public AbstractSymbol getElementAt(int index) {
        return list.get(index);
    }

    public void addListDataListener(ListDataListener l) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeListDataListener(ListDataListener l) {
//        throw new UnsupportedOperationException("Not supported yet.");
    }

}
