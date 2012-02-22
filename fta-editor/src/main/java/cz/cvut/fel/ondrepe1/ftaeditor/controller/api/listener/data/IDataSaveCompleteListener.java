package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataSaveCompleteEvent;

/**
 *
 * @author ondrepe
 */
public interface IDataSaveCompleteListener {

    public void onEvent(DataSaveCompleteEvent event);
}
