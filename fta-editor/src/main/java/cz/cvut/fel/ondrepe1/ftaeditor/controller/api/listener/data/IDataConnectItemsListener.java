package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataConnectItemsEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.ILocalEventListener;

/**
 *
 * @author ondrepe
 */
public interface IDataConnectItemsListener extends ILocalEventListener {

    public void onEvent(DataConnectItemsEvent event);
}
