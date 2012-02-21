package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataSaveEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.IEventListener;

/**
 *
 * @author ondrepe
 */
public interface IDataSaveListener extends IEventListener {

    public void onEvent(DataSaveEvent event);
}
