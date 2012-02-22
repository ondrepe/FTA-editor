package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataChangedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.ILocalEventListener;

/**
 *
 * @author ondrepe
 */
public interface IDataChangedListener extends ILocalEventListener {

    public void onEvent(DataChangedEvent event);
}
