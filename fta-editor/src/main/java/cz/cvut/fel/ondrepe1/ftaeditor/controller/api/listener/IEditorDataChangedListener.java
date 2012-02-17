package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.EditorDataChangedEvent;

/**
 *
 * @author ondrepe
 */
public interface IEditorDataChangedListener extends IEventListener{

    public void onEvent(EditorDataChangedEvent event);
}
