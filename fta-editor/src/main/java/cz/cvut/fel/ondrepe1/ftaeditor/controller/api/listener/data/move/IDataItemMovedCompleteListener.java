package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data.move;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.move.DataItemMovedCompleteEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.IEventListener;

/**
 *
 * @author ondrepe
 */
public interface IDataItemMovedCompleteListener extends IEventListener {

    public void onEvent(DataItemMovedCompleteEvent event);
}
