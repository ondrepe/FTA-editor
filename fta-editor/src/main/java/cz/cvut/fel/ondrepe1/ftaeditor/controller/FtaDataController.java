package cz.cvut.fel.ondrepe1.ftaeditor.controller;

import cz.cvut.fel.ondrepe1.ftaeditor.TestDataFactory;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataAddLonelyItemEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataChangedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataCreateItemEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data.IDataCreateItemListener;
import cz.cvut.fel.ondrepe1.ftaeditor.data.DataCreator;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaData;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaDataItem;

/**
 *
 * @author ondrejicek
 */
public class FtaDataController implements IDataCreateItemListener {
    
    private static FtaDataController instance;
    
    public static FtaDataController getInstance() {
        if (instance == null) {
            instance = new FtaDataController();
        }
        return instance;
    }
    
    private FtaData data;
    
    private FtaDataItem parent;

    public FtaDataController() {
        registerListeners();
    }
    
    public FtaData getData() {
        return data;
    }

    public FtaDataItem getParent() {
        return parent;
    }
    
    public boolean hasParent() {
        return parent != null;
    }

    public void setParent(FtaDataItem parent) {
        this.parent = parent;
    }
    
    public void resetParent() {
        parent = null;
    }
    
    public void loadData() {
        data = TestDataFactory.generateFTATree();
        FtaController.getInstance().fireEvent(new DataChangedEvent(data));
    }
    
    private void registerListeners() {
        FtaController.getInstance().registerEventListener(DataCreateItemEvent.class, this);
    }

    public void onEvent(DataCreateItemEvent event) {
        FtaDataItem item = DataCreator.createItem(event.getEditorState(), event.getPosition());
        FtaController.getInstance().fireEvent(new DataAddLonelyItemEvent(item));
    }
}
