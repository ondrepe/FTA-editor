package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.editor;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.editor.EditorToolbarButtonChangeEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.IGlobalEventListener;

/**
 *
 * @author ondrepe
 */
public interface IEditorToolbarButtonChangeListener extends IGlobalEventListener {

    public void onEvent(EditorToolbarButtonChangeEvent event);
}
