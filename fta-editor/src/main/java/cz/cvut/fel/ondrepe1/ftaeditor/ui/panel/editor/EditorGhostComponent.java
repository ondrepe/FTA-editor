package cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.editor;

import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaData;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaDataItem;
import cz.cvut.fel.ondrepe1.ftaeditor.listener.editor.EditorGhostComponentMouseListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;

/**
 *
 * @author ondrejicek
 */
public class EditorGhostComponent extends JComponent {

    private FtaDataItem dataItem;
    boolean dragged;
    
    public EditorGhostComponent(FtaDataItem dataItem, Rectangle2D rectangle) {
        this.dataItem = dataItem;
        this.setBounds(rectangle.getBounds());
        registerListeners();
        
    }

    public FtaDataItem getDataItem() {
        return dataItem;
    }

    private void registerListeners() {
        EditorGhostComponentMouseListener listener = new EditorGhostComponentMouseListener(this);
        addMouseListener(listener);
        addMouseMotionListener(listener);
    }
}
