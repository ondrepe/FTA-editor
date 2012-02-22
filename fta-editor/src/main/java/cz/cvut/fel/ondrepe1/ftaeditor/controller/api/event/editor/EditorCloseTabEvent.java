package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.editor;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.CommonGlobalEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.editor.EditorTabbedPanelItem;

/**
 *
 * @author ondrepe
 */
public class EditorCloseTabEvent extends CommonGlobalEvent {

    private EditorTabbedPanelItem editorItem;

    public EditorCloseTabEvent(EditorTabbedPanelItem editorItem) {
        this.editorItem = editorItem;
    }

    public EditorTabbedPanelItem getEditorItem() {
        return editorItem;
    }
}
