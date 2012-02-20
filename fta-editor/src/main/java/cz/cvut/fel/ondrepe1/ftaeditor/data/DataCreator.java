package cz.cvut.fel.ondrepe1.ftaeditor.data;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaEditorController;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.SvgGroupObject;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.event.SvgBasicEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.event.SvgConditionalEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.gate.SvgAndGate;
import java.awt.Point;

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
    
    public static FtaDataItem createItem(int state, Point position) {
        SvgGroupObject svgObject = null;
        if (state == FtaEditorController.EDITOR_STATE_AND_GATE) {
            svgObject = new SvgAndGate(position.x, position.y);
        } else if (state == FtaEditorController.EDITOR_STATE_BASIC_EVENT) {
            svgObject = new SvgBasicEvent(position.x, position.y);
        } else if (state == FtaEditorController.EDITOR_STATE_CONDITIONAL_EVENT) {
            svgObject = new SvgConditionalEvent(position.x, position.y);
        }
        FtaDataItem item = null;
        if (svgObject != null) {
            item = new FtaDataItem(svgObject);
        }
        return item;
    }
}
