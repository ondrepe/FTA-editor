package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.symbol;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.symbol.RemoveSymbolEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.IEventListener;

/**
 *
 * @author ondrepe
 */
public interface IRemoveSymbolListener extends IEventListener {

    public void onEvent(RemoveSymbolEvent event);
}
