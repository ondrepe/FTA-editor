package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.editor;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.editor.EditorAddTabEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.IGlobalEventListener;

/**
 *
 * @author ondrepe
 */
public interface IEditorAddTabListener extends IGlobalEventListener {

    public void onEvent(EditorAddTabEvent event);
}
