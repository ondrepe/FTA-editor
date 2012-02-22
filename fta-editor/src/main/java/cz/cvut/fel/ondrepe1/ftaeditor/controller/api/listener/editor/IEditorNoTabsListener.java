package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.editor;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.editor.EditorNoTabsEvent;

/**
 *
 * @author ondrepe
 */
public interface IEditorNoTabsListener {

    public void onEvent(EditorNoTabsEvent event);
}
