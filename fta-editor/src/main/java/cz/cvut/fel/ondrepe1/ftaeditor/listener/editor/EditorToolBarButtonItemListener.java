package cz.cvut.fel.ondrepe1.ftaeditor.listener.editor;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.editor.EditorToolbarButtonChangeEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.editor.toolbar.EditorToolbarToggleButton;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 *
 * @author ondrepe
 */
public class EditorToolBarButtonItemListener implements ItemListener {

    public void itemStateChanged(ItemEvent e) {
        EditorToolbarToggleButton button = (EditorToolbarToggleButton) e.getItem();
        boolean selected = e.getStateChange() == 1;
        FtaController.getInstance().fireEvent(new EditorToolbarButtonChangeEvent(button, selected));
    }

}
