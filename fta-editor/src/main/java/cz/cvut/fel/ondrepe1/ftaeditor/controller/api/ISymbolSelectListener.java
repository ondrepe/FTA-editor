package cz.cvut.fel.ondrepe1.ftaeditor.controller.api;

import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;

/**
 *
 * @author ondrepe
 */
public interface ISymbolSelectListener extends IEventListener {

    public void onSelect(AbstractSymbol symbol);
}
