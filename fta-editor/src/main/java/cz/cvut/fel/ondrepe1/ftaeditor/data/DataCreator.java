package cz.cvut.fel.ondrepe1.ftaeditor.data;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaEditorController;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.SvgGroupObject;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.event.SvgBasicEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.event.SvgConditionalEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.event.SvgDormantEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.event.SvgUndevelopedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.svg.gate.*;
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
        } else if (state == FtaEditorController.EDITOR_STATE_DORMANT_EVENT) {
            svgObject = new SvgDormantEvent(position.x, position.y);
        } else if (state == FtaEditorController.EDITOR_STATE_UNDEVELOPED_EVENT) {
            svgObject = new SvgUndevelopedEvent(position.x, position.y);
        } else if (state == FtaEditorController.EDITOR_STATE_PAND_GATE) {
            svgObject = new SvgPriorityAndGate(position.x, position.y);
        } else if (state == FtaEditorController.EDITOR_STATE_INHIBIT_GATE) {
            svgObject = new SvgInhibitGate(position.x, position.y);
        } else if (state == FtaEditorController.EDITOR_STATE_OR_GATE) {
            svgObject = new SvgOrGate(position.x, position.y);
        } else if (state == FtaEditorController.EDITOR_STATE_MAJORITY_VOTE_GATE) {
            svgObject = new SvgMajorityVoteGate(position.x, position.y);
        } else if (state == FtaEditorController.EDITOR_STATE_XOR_GATE) {
            svgObject = new SvgExclusiveOrGate(position.x, position.y);
        } else if (state == FtaEditorController.EDITOR_STATE_NOT_GATE) {
            svgObject = new SvgNotGate(position.x, position.y);
        }  else if (state == FtaEditorController.EDITOR_STATE_TRANSFER_GATE) {
            svgObject = new SvgTransferGate(position.x, position.y);
        }
        FtaDataItem item = null;
        if (svgObject != null) {
            item = new FtaDataItem(svgObject);
        }
        return item;
    }
}
