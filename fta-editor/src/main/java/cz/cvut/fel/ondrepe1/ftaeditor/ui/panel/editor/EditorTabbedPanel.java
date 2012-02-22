package cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.editor;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JTabbedPane;

/**
 *
 * @author ondrejicek
 */
public class EditorTabbedPanel extends JTabbedPane {

    private List<EditorTabbedPanelItem> items;

    public EditorTabbedPanel() {
        items = new ArrayList<EditorTabbedPanelItem>();
    }
    
    public void addItem(EditorTabbedPanelItem item) {
        this.addTab(item.getTitle(), item);
        items.add(item);
    }
    
    public boolean hasItems() {
        return !items.isEmpty();
    }
    
    public EditorTabbedPanelItem getSelectedItem() {
        return (EditorTabbedPanelItem) this.getSelectedComponent();
//        this.add
    }
}
