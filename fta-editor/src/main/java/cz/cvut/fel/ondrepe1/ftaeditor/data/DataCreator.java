package cz.cvut.fel.ondrepe1.ftaeditor.data;

import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.event.SvgBasicEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.event.SvgConditionalEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.gate.SvgAndGate;

/**
 *
 * @author ondrepe
 */
public class DataCreator {

    public static FtaDataItem createBasicEvent(int x, int y) {
        return new FtaDataItem(new SvgBasicEvent(x, y));
    }
    
    public static FtaDataItem createConditionalEvent(int x, int y) {
        return new FtaDataItem(new SvgConditionalEvent(x, y));
    }
    
    
    public static FtaDataItem createAndGate(int x, int y) {
        return new FtaDataItem(new SvgAndGate(x, y));
    }
}
