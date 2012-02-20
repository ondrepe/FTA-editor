package cz.cvut.fel.ondrepe1.ftaeditor.controller;

import cz.cvut.fel.ondrepe1.ftaeditor.TestDataFactory;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataAddLonelyItemEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataChangedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.data.DataCreateItemEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.symbol.AddSymbolEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.symbol.RemoveSymbolEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.data.IDataCreateItemListener;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.symbol.IAddSymbolListener;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.listener.symbol.IRemoveSymbolListener;
import cz.cvut.fel.ondrepe1.ftaeditor.data.DataCreator;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaData;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaDataItem;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate.AbstractGate;

/**
 *
 * @author ondrejicek
 */
public class FtaDataController implements IRemoveSymbolListener, IAddSymbolListener, IDataCreateItemListener {
    
    private static FtaDataController instance;
    
    public static FtaDataController getInstance() {
        if (instance == null) {
            instance = new FtaDataController();
        }
        return instance;
    }
    
    private FtaData data;

    public FtaDataController() {
        registerListeners();
    }
    
    public FtaData getData() {
        return data;
    }
    
    public void loadData() {
        data = TestDataFactory.generateFTATree();
        FtaController.getInstance().fireEvent(new DataChangedEvent(data));
    }

    public void onRemoveSymbol(AbstractSymbol symbol) {
//        AbstractGate gate = symbol.getParent();
//        if (gate == null) {
//            data = null;
//        } else {
//            gate.getChildren().remove(symbol);
//        }
        FtaController.getInstance().fireEvent(new DataChangedEvent(data));
    }
    
    public void onAddSymbol(AbstractSymbol newSymbol, AbstractGate parent) {
//        parent.addChild(newSymbol);
        FtaController.getInstance().fireEvent(new DataChangedEvent(data));
    }
    
    private void registerListeners() {
        FtaController.getInstance().registerEventListener(AddSymbolEvent.class, this);
        FtaController.getInstance().registerEventListener(RemoveSymbolEvent.class, this);
        FtaController.getInstance().registerEventListener(DataCreateItemEvent.class, this);
    }

    public void onEvent(RemoveSymbolEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onEvent(AddSymbolEvent event) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void onEvent(DataCreateItemEvent event) {
        FtaDataItem item = DataCreator.createItem(event.getEditorState(), event.getPosition());
        FtaController.getInstance().fireEvent(new DataAddLonelyItemEvent(item));
    }
}
