package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.symbol;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.CommonEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;

/**
 *
 * @author ondrepe
 */
public class CommonSymbolEvent extends CommonEvent {

    private AbstractSymbol symbol;

    public CommonSymbolEvent(AbstractSymbol symbol) {
        this.symbol = symbol;
    }

    public AbstractSymbol getSymbol() {
        return symbol;
    }
}
