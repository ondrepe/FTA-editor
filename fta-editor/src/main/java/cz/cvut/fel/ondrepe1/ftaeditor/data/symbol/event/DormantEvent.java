package cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ondrepe
 */
@XmlRootElement(name="dormantEvent")
public class DormantEvent extends AbstractEvent {

    @Override
    public String getType() {
        return "Neaktivní";
    }
}
