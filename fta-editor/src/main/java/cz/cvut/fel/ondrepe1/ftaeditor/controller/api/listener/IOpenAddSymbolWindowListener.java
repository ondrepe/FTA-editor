package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.OpenAddSymbolWindowEvent;

/**
 *
 * @author ondrepe
 */
public interface IOpenAddSymbolWindowListener extends IEventListener {

    public void onEvent(OpenAddSymbolWindowEvent event);
}
