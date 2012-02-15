package cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.icon;

import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event.BasicEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event.ConditionalEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event.DormantEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event.UndevelopedEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate.*;
import org.jdesktop.swingx.renderer.StringValue;
import org.jdesktop.swingx.renderer.StringValues;

/**
 *
 * @author ondrepe
 */
public class DiagramTreeIconStringValue implements StringValue {

    public String getString(Object value) {
        if (value instanceof AbstractSymbol) {
            String key = null;
            if (value instanceof AbstractGate) {
                key = "gate/";
                if (value instanceof AndGate) {
                    key += "and";
                } else if (value instanceof InhibitGate) {
                    key += "inhibit";
                } else if (value instanceof MajorityVoteGate) {
                    key += "majorityVote";
                } else if (value instanceof NotGate) {
                    key += "not";
                } else if (value instanceof OrGate) {
                    key += "or";
                } else if (value instanceof PriorityAndGate) {
                    key += "pand";
//                } else if (value instanceof TransferGate) {
//                    key += "transfer";
                } else if (value instanceof ExclusiveOrGate) {
                    key += "xor";
                }
            } else {
                key = "event/";
                if (value instanceof BasicEvent) {
                    key += "basic";
                } else if (value instanceof ConditionalEvent) {
                    key += "conditional";
                } else if (value instanceof DormantEvent) {
                    key += "dormant";
                } else if (value instanceof UndevelopedEvent) {
                    key += "undeveloped";
                }
            }
            return key + ".svg";
        }
        return StringValues.TO_STRING.getString(value); 
    }

}
