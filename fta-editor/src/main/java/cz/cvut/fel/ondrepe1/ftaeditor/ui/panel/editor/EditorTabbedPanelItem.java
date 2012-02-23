package cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.editor;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataChangedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data.IDataChangedListener;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.editor.canvas.EditorCanvas;
import javax.swing.JScrollPane;

/**
 *
 * @author ondrejicek
 */
public class EditorTabbedPanelItem extends JScrollPane implements IDataChangedListener {

    public static final String STAR = " *";
    
    private String title;
    private EditorCanvas canvas;
    
    public EditorTabbedPanelItem() {
        this(null);
    }
    
    public EditorTabbedPanelItem(String title) {
        if(title == null) {
           this.title = "Nový" + STAR; 
        } else {
            if (title.endsWith(".fta") && title.length() > 4) {
                this.title = title.substring(0, title.length() - 4);
            } else {
                this.title = title;
            }
        }
        canvas = new EditorCanvas();
        this.setViewportView(canvas);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void onEvent(DataChangedEvent event) {
        if(this.getParent() instanceof EditorTabbedPanel) {
            EditorTabbedPanel parent = (EditorTabbedPanel) getParent();
            try {
                String tabTitle = parent.getTitleAt(parent.getSelectedIndex());
                if (!tabTitle.endsWith(STAR)) {
                    tabTitle += STAR;
                    parent.setTitleAt(parent.getSelectedIndex(), tabTitle);
                }
            } catch(ArrayIndexOutOfBoundsException ex) {
                // do nothing, because Tab is allready closed
            }
        }
    }
}
