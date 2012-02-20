package cz.cvut.fel.ondrepe1.ftaeditor.listener.editor;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaEditorController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.move.DataItemMovedCompleteEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.move.DataItemMovedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.editor.canvas.EditorCanvasItem;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author ondrejicek
 */
public class EditorCanvasItemMouseListener extends MouseAdapter {

    private EditorCanvasItem component;
    private Point initialPoint;

    public EditorCanvasItemMouseListener(EditorCanvasItem component) {
        this.component = component;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        int editorState = FtaEditorController.getInstance().getEditorState();
        
        if (editorState == FtaEditorController.EDITOR_STATE_SELECT 
                || editorState == FtaEditorController.EDITOR_STATE_EDIT
                || editorState == FtaEditorController.EDITOR_STATE_CONNECT) {
            component.setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        int editorState = FtaEditorController.getInstance().getEditorState();
        
        if (editorState == FtaEditorController.EDITOR_STATE_SELECT 
                || editorState == FtaEditorController.EDITOR_STATE_EDIT
                || editorState == FtaEditorController.EDITOR_STATE_CONNECT) {
            component.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int editorState = FtaEditorController.getInstance().getEditorState();
        System.out.println("Start: x: " + component.getX() + " y: " + component.getY());
        
        if (editorState == FtaEditorController.EDITOR_STATE_SELECT) {
            initialPoint = e.getPoint();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int editorState = FtaEditorController.getInstance().getEditorState();
        
        if (editorState == FtaEditorController.EDITOR_STATE_SELECT) {
            Rectangle rect = component.getBounds();
            component.setBounds(rect.x + e.getX() - initialPoint.x, rect.y + e.getY() - initialPoint.y, rect.width, rect.height);
            component.repaint();

            FtaController.getInstance().fireEvent(new DataItemMovedEvent(component.getDataItem(), e, initialPoint));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int editorState = FtaEditorController.getInstance().getEditorState();
        
        System.out.println("Stop: x: " + component.getX() + " y: " + component.getY() + "; component: " + component.toString());
        if (editorState == FtaEditorController.EDITOR_STATE_SELECT) {
            FtaController.getInstance().fireEvent(new DataItemMovedCompleteEvent(component.getDataItem(), new Point(component.getX(), component.getY())));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Clicked: x: " + e.getX() + " y: " + e.getY() + "; component: " + component.toString());
    }
}
