package cz.cvut.fel.ondrepe1.ftaeditor.ui.window.model;

import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event.BasicEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event.ConditionalEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event.DormantEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event.UndevelopedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ondrepe
 */
public class TypeComboBoxDataFactory {

    private static TypeComboBoxDataFactory instance;

    public static TypeComboBoxDataFactory getInstance() {
        if (instance == null) {
            instance = new TypeComboBoxDataFactory();
        }
        return instance;
    }
    
    private List<AbstractSymbol> list;

    public TypeComboBoxDataFactory() {
        list = new ArrayList<AbstractSymbol>();
        fillSymbolsList();
    }

    public List<AbstractSymbol> getSymbols() {
        return list;
    }
    
    private void fillSymbolsList() {
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
}
