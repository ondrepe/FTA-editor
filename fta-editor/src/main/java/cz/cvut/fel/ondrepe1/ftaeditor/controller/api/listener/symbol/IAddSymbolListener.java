package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.symbol;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.symbol.AddSymbolEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.IEventListener;

/**
 *
 * @author ondrepe
 */
public interface IAddSymbolListener extends IEventListener {

    public void onEvent(AddSymbolEvent event);
}
