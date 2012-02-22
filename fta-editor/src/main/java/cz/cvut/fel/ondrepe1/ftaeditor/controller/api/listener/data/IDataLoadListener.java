package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataLoadEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.IGlobalEventListener;

/**
 *
 * @author ondrepe
 */
public interface IDataLoadListener extends IGlobalEventListener {

    public void onEvent(DataLoadEvent event);
}
