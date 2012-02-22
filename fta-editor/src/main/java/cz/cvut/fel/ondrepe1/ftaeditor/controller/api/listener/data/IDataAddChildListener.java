package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataAddChildEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.ILocalEventListener;

/**
 *
 * @author ondrejicek
 */
public interface IDataAddChildListener extends ILocalEventListener {
    
    public void onEvent(DataAddChildEvent event);
}
