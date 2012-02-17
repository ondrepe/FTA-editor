package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.editor;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.editor.EditorToolbarButtonChangeEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.IEventListener;

/**
 *
 * @author ondrepe
 */
public interface IEditorToolbarButtonChangeListener extends IEventListener {

    public void onEvent(EditorToolbarButtonChangeEvent event);
}
