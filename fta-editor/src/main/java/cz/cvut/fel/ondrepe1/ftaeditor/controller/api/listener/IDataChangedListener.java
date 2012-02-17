package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.DataChangedEvent;

/**
 *
 * @author ondrepe
 */
public interface IDataChangedListener extends IEventListener {

    public void onEvent(DataChangedEvent event);
}
