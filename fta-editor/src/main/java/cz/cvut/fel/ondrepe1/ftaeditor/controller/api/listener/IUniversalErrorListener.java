package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.UniversalErrorEvent;

/**
 *
 * @author ondrepe
 */
public interface IUniversalErrorListener extends IGlobalEventListener {

    public void onEvent(UniversalErrorEvent event);
}
