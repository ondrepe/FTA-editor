package cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.editor.toolbar;

import org.apache.batik.util.gui.resource.JToolbarToggleButton;

/**
 *
 * @author ondrepe
 */
public class EditorToolbarToggleButton extends JToolbarToggleButton {

    private int editorState;
    
    public EditorToolbarToggleButton(int editorState) {
        this.editorState = editorState;
    }

    public int getEditorState() {
        return editorState;
    }
}
