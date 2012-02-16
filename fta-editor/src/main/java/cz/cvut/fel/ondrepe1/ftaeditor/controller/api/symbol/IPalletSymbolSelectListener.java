package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.symbol;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.IEventListener;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;

/**
 *
 * @author ondrepe
 */
public interface IPalletSymbolSelectListener extends IEventListener {

    public void onPalletSelect(AbstractSymbol symbol);
}
