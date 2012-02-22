package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data.move;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.move.DataItemMovedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.ILocalEventListener;

/**
 *
 * @author ondrejicek
 */
public interface IDataItemMovedListener extends ILocalEventListener {

    public void onEvent(DataItemMovedEvent event);
}
