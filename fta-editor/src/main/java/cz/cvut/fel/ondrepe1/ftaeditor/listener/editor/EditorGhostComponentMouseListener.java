package cz.cvut.fel.ondrepe1.ftaeditor.listener.editor;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataItemMovedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.editor.EditorGhostComponent;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author ondrejicek
 */
public class EditorGhostComponentMouseListener extends MouseAdapter {

    private EditorGhostComponent component;

    public EditorGhostComponentMouseListener(EditorGhostComponent component) {
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
    public void mouseDragged(MouseEvent e) {
        FtaController.getInstance().fireEvent(new DataItemMovedEvent(component.getDataItem(), e));
        System.out.println("x: " + e.getX() + ", y: " + e.getY());
    }
}
