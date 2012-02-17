package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.editor;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.CommonEvent;
import org.apache.batik.util.gui.resource.JToolbarToggleButton;

/**
 *
 * @author ondrepe
 */
public class EditorToolbarButtonChangeEvent extends CommonEvent {

    private JToolbarToggleButton button;
    private boolean selected;

    public EditorToolbarButtonChangeEvent(JToolbarToggleButton button, boolean selected) {
        this.button = button;
        this.selected = selected;
    }

    public JToolbarToggleButton getButton() {
        return button;
    }

    public boolean isSelected() {
        return selected;
    }
}
