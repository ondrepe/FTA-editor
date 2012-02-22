package cz.cvut.fel.ondrepe1.ftaeditor.listener.editor;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaControllCenter;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaEditorController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.OpenEditDialogEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataConnectItemsEvent;
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
                || editorState == FtaEditorController.EDITOR_STATE_EDIT) {
            component.setCursor(new Cursor(Cursor.HAND_CURSOR));
        } else if (editorState == FtaEditorController.EDITOR_STATE_CONNECT) {
            if (!FtaEditorController.getInstance().hasParent()) {
                if (component.getDataItem().canAddChild()) {
                    component.setCursor(new Cursor(Cursor.HAND_CURSOR));
                } 
            } else {
                if (FtaEditorController.getInstance().getParent() != component.getDataItem()
                        && FtaEditorController.getInstance().getParent() != component.getDataItem().getParent())
                component.setCursor(new Cursor(Cursor.HAND_CURSOR));
                
            }
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

            FtaControllCenter.fireLocalEvent(new DataItemMovedEvent(component.getDataItem(), e, initialPoint));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int editorState = FtaEditorController.getInstance().getEditorState();
        
        if (editorState == FtaEditorController.EDITOR_STATE_SELECT) {
            FtaControllCenter.fireLocalEvent(new DataItemMovedCompleteEvent(component.getDataItem(), new Point(component.getX(), component.getY())));
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int editorState = FtaEditorController.getInstance().getEditorState();
        
        if (editorState == FtaEditorController.EDITOR_STATE_CONNECT) {
            if (!FtaEditorController.getInstance().hasParent()) {
                FtaEditorController.getInstance().setParent(component.getDataItem());
                component.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            } else {
                if (FtaEditorController.getInstance().getParent() != component.getDataItem()
                        && FtaEditorController.getInstance().getParent() != component.getDataItem().getParent()) {
                    component.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    FtaControllCenter.fireLocalEvent(new DataConnectItemsEvent(FtaEditorController.getInstance().getParent(), component.getDataItem()));
                    FtaEditorController.getInstance().resetParent();
                }
            }
        } else if (editorState == FtaEditorController.EDITOR_STATE_EDIT) {
            if (e.getClickCount() == 2) {
                FtaControllCenter.fireGlobalEvent(new OpenEditDialogEvent(component.getDataItem()));
            }
        }
    }
}
