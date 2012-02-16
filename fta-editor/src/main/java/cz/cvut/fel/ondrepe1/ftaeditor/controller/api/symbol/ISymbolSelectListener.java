package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.symbol;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.IEventListener;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;

/**
 *
 * @author ondrepe
 */
public interface ISymbolSelectListener extends IEventListener {

    public void onSelect(AbstractSymbol symbol);
}
