package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.editor;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.CommonEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.editor.toolbar.EditorToolbarToggleButton;

/**
 *
 * @author ondrepe
 */
public class EditorToolbarButtonChangeEvent extends CommonEvent {

    private EditorToolbarToggleButton button;
    private boolean selected;

    public EditorToolbarButtonChangeEvent(EditorToolbarToggleButton button, boolean selected) {
        this.button = button;
        this.selected = selected;
    }

    public EditorToolbarToggleButton getButton() {
        return button;
    }

    public boolean isSelected() {
        return selected;
    }
}
