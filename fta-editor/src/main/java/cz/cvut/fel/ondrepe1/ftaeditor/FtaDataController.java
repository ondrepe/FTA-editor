package cz.cvut.fel.ondrepe1.ftaeditor;

import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;

/**
 *
 * @author ondrejicek
 */
public class FtaDataController {
    
    public AbstractSymbol getData() {
        return TestDataFactory.generateFTATree();
    }
}
