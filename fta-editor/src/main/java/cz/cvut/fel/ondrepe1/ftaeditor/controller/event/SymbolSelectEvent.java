package cz.cvut.fel.ondrepe1.ftaeditor.controller.event;

import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;

/**
 *
 * @author ondrepe
 */
public class SymbolSelectEvent extends CommonEvent {

    private AbstractSymbol object;

    public SymbolSelectEvent(AbstractSymbol object) {
        this.object = object;
    }
    
    public AbstractSymbol getObject() {
        return object;
    }
}
