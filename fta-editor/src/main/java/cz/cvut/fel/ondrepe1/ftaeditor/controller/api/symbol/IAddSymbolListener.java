package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.symbol;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.IEventListener;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate.AbstractGate;

/**
 *
 * @author ondrepe
 */
public interface IAddSymbolListener extends IEventListener {

    public void onAddSymbol(AbstractSymbol newSymbol, AbstractGate parent);
}
