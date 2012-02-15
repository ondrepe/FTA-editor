package cz.cvut.fel.ondrepe1.ftaeditor.controller;

import cz.cvut.fel.ondrepe1.ftaeditor.TestDataFactory;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;

/**
 *
 * @author ondrejicek
 */
public class FtaDataController {
    
    private AbstractSymbol data;
    
    public AbstractSymbol getData() {
        return TestDataFactory.generateFTATree();
    }
}
