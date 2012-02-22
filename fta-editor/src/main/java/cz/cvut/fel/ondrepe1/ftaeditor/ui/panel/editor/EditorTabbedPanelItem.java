package cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.editor;

import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.editor.canvas.EditorCanvas;
import javax.swing.JScrollPane;

/**
 *
 * @author ondrejicek
 */
public class EditorTabbedPanelItem extends JScrollPane {

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
}
