package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.symbol;

import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;

/**
 *
 * @author ondrepe
 */
public class PalletSymbolSelectEvent extends CommonSymbolEvent {

    public PalletSymbolSelectEvent(AbstractSymbol symbol) {
        super(symbol);
    }
}
