package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.OpenEditDialogEvent;

/**
 *
 * @author ondrepe
 */
public interface IOpenEditDialogListener extends IEventListener {

    public void onEvent(OpenEditDialogEvent event);
}
