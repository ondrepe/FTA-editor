package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataRemoveItemEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.ILocalEventListener;

/**
 *
 * @author ondrepe
 */
public interface IDataRemoveItemListener extends ILocalEventListener {

    public void onEvent(DataRemoveItemEvent event);
}
