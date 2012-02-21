package cz.cvut.fel.ondrepe1.ftaeditor.ui.panel.diagram.model.icon;

import cz.cvut.fel.ondrepe1.ftaeditor.data.FtaDataItem;
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
        if (value instanceof FtaDataItem) {
            Class clazz = ((FtaDataItem) value).getSymbolClass();
            if (clazz != null) {
                String key;
                if (clazz.getSuperclass().equals(AbstractGate.class)) {
                    key = "gate/";
                    if (clazz.equals(AndGate.class)) {
                        key += "and";
                    } else if (clazz.equals(InhibitGate.class)) {
                        key += "inhibit";
                    } else if (clazz.equals(MajorityVoteGate.class)) {
                        key += "majorityVote";
                    } else if (clazz.equals(NotGate.class)) {
                        key += "not";
                    } else if (clazz.equals(OrGate.class)) {
                        key += "or";
                    } else if (clazz.equals(PriorityAndGate.class)) {
                        key += "pand";
//                } else if (clazz.equals(TransferGate.class)) {
//                    key += "transfer";
                    } else if (clazz.equals(ExclusiveOrGate.class)) {
                        key += "xor";
                    }
                } else {
                    key = "event/";
                    if (clazz.equals(BasicEvent.class)) {
                        key += "basic";
                    } else if (clazz.equals(ConditionalEvent.class)) {
                        key += "conditional";
                    } else if (clazz.equals(DormantEvent.class)) {
                        key += "dormant";
                    } else if (clazz.equals(UndevelopedEvent.class)) {
                        key += "undeveloped";
                    }
                }
                return key + ".svg";
            }
        }
        return StringValues.TO_STRING.getString(value);
    }
}
