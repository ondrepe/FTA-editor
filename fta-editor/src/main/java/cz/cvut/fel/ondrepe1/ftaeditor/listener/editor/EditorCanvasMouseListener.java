package cz.cvut.fel.ondrepe1.ftaeditor.listener.editor;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaEditorController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataCreateItemEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.editor.canvas.EditorCanvas;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author ondrepe
 */
public class EditorCanvasMouseListener extends MouseAdapter {

    private EditorCanvas component;

    public EditorCanvasMouseListener(EditorCanvas component) {
        this.component = component;
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        int editorState = FtaEditorController.getInstance().getEditorState();
        
        if (editorState != FtaEditorController.EDITOR_STATE_SELECT 
                && editorState != FtaEditorController.EDITOR_STATE_EDIT 
                && editorState != FtaEditorController.EDITOR_STATE_CONNECT
                && editorState != FtaEditorController.EDITOR_STATE_NONE) {
            component.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        int editorState = FtaEditorController.getInstance().getEditorState();
        
        if (editorState != FtaEditorController.EDITOR_STATE_SELECT 
                && editorState != FtaEditorController.EDITOR_STATE_EDIT
                && editorState != FtaEditorController.EDITOR_STATE_CONNECT
                && editorState != FtaEditorController.EDITOR_STATE_NONE) {
            component.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int editorState = FtaEditorController.getInstance().getEditorState();
        
        if (editorState != FtaEditorController.EDITOR_STATE_SELECT 
                && editorState != FtaEditorController.EDITOR_STATE_EDIT
                && editorState != FtaEditorController.EDITOR_STATE_CONNECT
                && editorState != FtaEditorController.EDITOR_STATE_NONE) {
            FtaController.getInstance().fireEvent(new DataCreateItemEvent(e.getPoint(), editorState));
        }
    }
}
