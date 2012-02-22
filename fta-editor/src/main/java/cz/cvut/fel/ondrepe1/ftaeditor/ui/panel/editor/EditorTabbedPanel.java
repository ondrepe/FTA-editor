package cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.editor;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaControllCenter;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaEditorController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.editor.EditorAddTabEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.editor.EditorCloseTabEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.editor.EditorNoTabsEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.editor.EditorTabChangedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.editor.IEditorAddTabListener;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.editor.IEditorCloseTabListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author ondrejicek
 */
public class EditorTabbedPanel extends JTabbedPane implements ChangeListener, IEditorAddTabListener, IEditorCloseTabListener {

    private List<EditorTabbedPanelItem> items;

    public EditorTabbedPanel() {
        items = new ArrayList<EditorTabbedPanelItem>();
        EditorTabbedPanelUI eUi = new EditorTabbedPanelUI();
        setUI(eUi);
        addMouseListener(eUi);
        addMouseMotionListener(eUi);
        registerListeners();
    }
    
    public void addItem(EditorTabbedPanelItem item) {
        this.addTab(item.getTitle(), item);
        items.add(item);
        this.setSelectedComponent(item);
    }
    
    public boolean hasItems() {
        return !items.isEmpty();
    }
    
    public void closeItem(EditorTabbedPanelItem item) {
        remove(item);
        items.remove(item);
        if (!hasItems()) {
            FtaControllCenter.fireGlobalEvent(new EditorNoTabsEvent());
        }
    }
    
    public EditorTabbedPanelItem getSelectedItem() {
        return (EditorTabbedPanelItem) this.getSelectedComponent();
    }
    
    private void registerListeners() {
        addChangeListener(this);
        FtaControllCenter.registerGlobalEventListener(EditorCloseTabEvent.class, this);
        FtaControllCenter.registerGlobalEventListener(EditorAddTabEvent.class, this);
    }
    
    public void stateChanged(ChangeEvent e) {
        FtaControllCenter.fireGlobalEvent(new EditorTabChangedEvent((EditorTabbedPanelItem) this.getSelectedItem()));
        FtaEditorController.getInstance().resetParent();
    }

    public void onEvent(EditorCloseTabEvent event) {
        closeItem(event.getEditorItem());
    }

    public void onEvent(EditorAddTabEvent event) {
        addItem(event.getItem());
    }
}
