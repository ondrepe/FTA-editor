package cz.cvut.fel.ondrepe1.ftaeditor.listener.editor;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataItemMovedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.editor.EditorCanvasItem;
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
        component.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        component.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        initialPoint = new Point(e.getX(), e.getY());
//        System.out.println("click");
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Rectangle rect = component.getBounds();
        component.setBounds(rect.x + e.getX() - initialPoint.x, rect.y + e.getY() - initialPoint.y, rect.width, rect.height);
        component.repaint();
        
        FtaController.getInstance().fireEvent(new DataItemMovedEvent(component.getDataItem(), e, initialPoint));
//        System.out.println("drag");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        System.out.println("drop");
    }
    
    
}
