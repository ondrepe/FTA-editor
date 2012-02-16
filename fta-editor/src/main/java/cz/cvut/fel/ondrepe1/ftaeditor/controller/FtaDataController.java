package cz.cvut.fel.ondrepe1.ftaeditor.controller;

import cz.cvut.fel.ondrepe1.ftaeditor.TestDataFactory;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.DataChangedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.symbol.RemoveSymbolEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.symbol.IRemoveSymbolListener;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate.AbstractGate;

/**
 *
 * @author ondrejicek
 */
public class FtaDataController implements IRemoveSymbolListener {
    
    private static FtaDataController instance;
    
    public static FtaDataController getInstance() {
        if (instance == null) {
            instance = new FtaDataController();
        }
        return instance;
    }
    
    private AbstractSymbol data;

    public FtaDataController() {
        registerListeners();
    }
    
    public AbstractSymbol getData() {
        return data;
    }
    
    public void loadData() {
        data = TestDataFactory.generateFTATree();
        FtaController.getInstance().fireEvent(new DataChangedEvent(data));
    }

    public void onRemoveSymbol(AbstractSymbol symbol) {
        AbstractGate gate = symbol.getParent();
        if (gate == null) {
            data = null;
        } else {
            gate.getChildren().remove(symbol);
        }
        FtaController.getInstance().fireEvent(new DataChangedEvent(data));
    }
    
    private void registerListeners() {
        FtaController.getInstance().registerEventListener(RemoveSymbolEvent.class, this);
    }
}
