package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataCreateItemEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.ILocalEventListener;

/**
 *
 * @author ondrepe
 */
public interface IDataCreateItemListener extends ILocalEventListener {

    public void onEvent(DataCreateItemEvent event);
}
