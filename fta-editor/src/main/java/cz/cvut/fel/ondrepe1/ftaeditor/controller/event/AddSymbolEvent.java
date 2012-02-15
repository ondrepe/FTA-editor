package cz.cvut.fel.ondrepe1.ftaeditor.controller.event;

import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;

/**
 *
 * @author ondrepe
 */
public class AddSymbolEvent extends CommonEvent {

    AbstractSymbol symbol;

    public AddSymbolEvent(AbstractSymbol symbol) {
        this.symbol = symbol;
    }

    public AbstractSymbol getSymbol() {
        return symbol;
    }
}
