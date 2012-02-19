package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataItemMovedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.IEventListener;

/**
 *
 * @author ondrejicek
 */
public interface IDataItemMovedListener extends IEventListener {

    public void onEvent(DataItemMovedEvent event);
}
