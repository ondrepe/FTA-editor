package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.editor;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.editor.EditorTabChangedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.IGlobalEventListener;

/**
 *
 * @author ondrepe
 */
public interface IEditorTabChangedListener extends IGlobalEventListener {

    public void onEvent(EditorTabChangedEvent event);
}
