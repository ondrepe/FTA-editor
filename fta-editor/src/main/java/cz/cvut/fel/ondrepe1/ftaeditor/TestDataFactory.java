package cz.cvut.fel.ondrepe1.ftaeditor;

import cz.cvut.fel.ondrepe1.ftaeditor.data.DataCreator;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaData;
import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaDataItem;

/**
 *
 * @author ondrepe
 */
public class TestDataFactory {

    public static FtaData generateFTATree() {
        FtaData data = new FtaData();
        
        FtaDataItem and = DataCreator.createAndGate(50, 50);
        and.addChild(DataCreator.createConditionalEvent(0, 200));
        and.addChild(DataCreator.createBasicEvent(100, 200));
        
        data.getStartItem().setChild(and);
        
        return data;
    }
}
