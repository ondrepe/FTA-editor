package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.editor;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.editor.EditorCloseTabEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.IGlobalEventListener;

/**
 *
 * @author ondrepe
 */
public interface IEditorCloseTabListener extends IGlobalEventListener {

    public void onEvent (EditorCloseTabEvent event);
}
