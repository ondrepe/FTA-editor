package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.symbol;

import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;

/**
 *
 * @author ondrepe
 */
public class AddSymbolEvent extends CommonSymbolEvent {

    private AbstractSymbol parent;
    
    public AddSymbolEvent(AbstractSymbol symbol, AbstractSymbol parent) {
        super(symbol);
        this.parent = parent;
    }

    public AbstractSymbol getParent() {
        return parent;
    }
}
