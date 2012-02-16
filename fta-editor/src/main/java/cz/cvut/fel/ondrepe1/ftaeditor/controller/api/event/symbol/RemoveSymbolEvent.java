package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.symbol;

import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;

/**
 *
 * @author ondrepe
 */
public class RemoveSymbolEvent extends CommonSymbolEvent {

    public RemoveSymbolEvent(AbstractSymbol symbol) {
        super(symbol);
    }
}
