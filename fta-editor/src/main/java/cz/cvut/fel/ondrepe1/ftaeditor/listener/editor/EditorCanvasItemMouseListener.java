package cz.cvut.fel.ondrepe1.ftaeditor.listener.editor;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaDataController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaEditorController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.OpenEditDialogEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataConnectItemsEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.move.DataItemMovedCompleteEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.move.DataItemMovedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaDataStartItem;
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
            if (!FtaDataController.getInstance().hasParent()) {
                if (component.getDataItem().canAddChild()) {
                    component.setCursor(new Cursor(Cursor.HAND_CURSOR));
                } 
            } else {
                if (FtaDataController.getInstance().getParent() != component.getDataItem() 
                        || component.getDataItem().getParent() instanceof FtaDataStartItem)
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
        int editorState = FtaEditorController.getInstance().getEditorState();
        
        if (editorState == FtaEditorController.EDITOR_STATE_CONNECT) {
            if (!FtaDataController.getInstance().hasParent()) {
                FtaDataController.getInstance().setParent(component.getDataItem());
                component.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            } else {
                if (FtaDataController.getInstance().getParent() != component.getDataItem() 
                        || component.getDataItem().getParent() instanceof FtaDataStartItem) {
                    component.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    FtaController.getInstance().fireEvent(new DataConnectItemsEvent(FtaDataController.getInstance().getParent(), component.getDataItem()));
                    FtaDataController.getInstance().resetParent();
                }
            }
        } else if (editorState == FtaEditorController.EDITOR_STATE_EDIT) {
            if (e.getClickCount() == 2) {
                FtaController.getInstance().fireEvent(new OpenEditDialogEvent(component.getDataItem()));
            }
        }
    }
}
