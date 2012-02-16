package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event;

import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;

/**
 *
 * @author ondrepe
 */
public class OpenAddSymbolWindowEvent extends CommonEvent {

    private AbstractSymbol parent;

    public OpenAddSymbolWindowEvent(AbstractSymbol parent) {
        this.parent = parent;
    }

    public AbstractSymbol getParent() {
        return parent;
    }
}
