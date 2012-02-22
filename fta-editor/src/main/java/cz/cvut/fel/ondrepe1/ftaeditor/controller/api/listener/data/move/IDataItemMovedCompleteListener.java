package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data.move;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.move.DataItemMovedCompleteEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.ILocalEventListener;

/**
 *
 * @author ondrepe
 */
public interface IDataItemMovedCompleteListener extends ILocalEventListener {

    public void onEvent(DataItemMovedCompleteEvent event);
}
