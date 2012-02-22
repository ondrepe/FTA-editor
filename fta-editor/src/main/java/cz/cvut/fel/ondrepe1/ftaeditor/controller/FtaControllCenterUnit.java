package cz.cvut.fel.ondrepe1.ftaeditor.controller;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.CommonLocalEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.*;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.move.DataItemMovedCompleteEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.ILocalEventListener;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaData;
import cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.editor.EditorTabbedPanelItem;

/**
 *
 * @author ondrejicek
 */
public class FtaControllCenterUnit {

    private FtaController ftaController;
    private FtaDataController ftaDataController;
    private EditorTabbedPanelItem editorTabbedPanelItem;
    private FtaData data;

    public FtaControllCenterUnit() {
        this(null, null);
    }
    
    public FtaControllCenterUnit(String name, FtaData data) {
        this.data = data;
        ftaController = new FtaController();
        ftaDataController = new FtaDataController();
        ftaController.registerEventListener(DataCreateItemEvent.class, ftaDataController);
        
        if (this.data == null) {
            this.data = new FtaData();
        }
        ftaController.registerEventListener(DataAddChildEvent.class, this.data);
        ftaController.registerEventListener(DataItemMovedCompleteEvent.class, this.data);
        ftaController.registerEventListener(DataAddLonelyItemEvent.class, this.data);
        ftaController.registerEventListener(DataConnectItemsEvent.class, this.data);
        ftaController.registerEventListener(DataEditItemEvent.class, this.data);
        ftaController.registerEventListener(DataRemoveItemEvent.class, this.data);
        
        editorTabbedPanelItem = new EditorTabbedPanelItem(name);
    }

    public FtaController getFtaController() {
        return ftaController;
    }
    
    public void fireEvent(CommonLocalEvent event) {
        ftaController.fireEvent(event);
    }
    
    public void registerEventListener(Class<? extends CommonLocalEvent> clazz, ILocalEventListener listener) {
        ftaController.registerEventListener(clazz, listener);
    }

    public EditorTabbedPanelItem getEditorTabbedPanelItem() {
        return editorTabbedPanelItem;
    }

    public FtaData getData() {
        return data;
    }

    public FtaDataController getFtaDataController() {
        return ftaDataController;
    }
}
