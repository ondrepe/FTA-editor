package cz.cvut.fel.ondrepe1.ftaeditor.ui.window.model;

import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event.BasicEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event.ConditionalEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event.DormantEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event.UndevelopedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate.*;
import java.util.ArrayList;
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
        list = new ArrayList<AbstractSymbol>();
        list.add(new BasicEvent());
        list.add(new ConditionalEvent());
        list.add(new DormantEvent());
        list.add(new UndevelopedEvent());
        
        list.add(new OrGate());
        list.add(new AndGate());
        list.add(new NotGate());
        list.add(new ExclusiveOrGate());
        list.add(new MajorityVoteGate());
        list.add(new PriorityAndGate());
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
