package cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.pallet.model;

import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import java.util.List;
import javax.swing.ListModel;
import javax.swing.event.ListDataListener;

/**
 *
 * @author ondrepe
 */
public class PalletListModel implements ListModel<AbstractSymbol> {

    private List<AbstractSymbol> list;

    public PalletListModel(List<AbstractSymbol> list) {
        this.list = list;
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
