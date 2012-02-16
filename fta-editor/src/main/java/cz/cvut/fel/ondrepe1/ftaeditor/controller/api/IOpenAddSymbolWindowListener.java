package cz.cvut.fel.ondrepe1.ftaeditor.controller.api;

import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;

/**
 *
 * @author ondrepe
 */
public interface IOpenAddSymbolWindowListener extends IEventListener {

    public void onOpenAddSymbolWindow(AbstractSymbol parent);
}
