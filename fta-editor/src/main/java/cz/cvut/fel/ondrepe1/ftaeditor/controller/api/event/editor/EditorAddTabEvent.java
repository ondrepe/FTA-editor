package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.editor;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.CommonGlobalEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.editor.EditorTabbedPanelItem;

/**
 *
 * @author ondrepe
 */
public class EditorAddTabEvent extends CommonGlobalEvent {

    private EditorTabbedPanelItem item;

    public EditorAddTabEvent(EditorTabbedPanelItem item) {
        this.item = item;
    }

    public EditorTabbedPanelItem getItem() {
        return item;
    }
}
