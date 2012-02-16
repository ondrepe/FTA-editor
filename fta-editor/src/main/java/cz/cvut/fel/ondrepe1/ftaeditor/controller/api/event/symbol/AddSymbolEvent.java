package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.symbol;

import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;

/**
 *
 * @author ondrepe
 */
public class AddSymbolEvent extends CommonSymbolEvent {

    public AddSymbolEvent(AbstractSymbol symbol) {
        super(symbol);
    }
}
