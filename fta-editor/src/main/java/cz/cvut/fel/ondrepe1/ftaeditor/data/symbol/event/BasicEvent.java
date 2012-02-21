package cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ondrepe
 */
@XmlRootElement(name="basicEvent")
public class BasicEvent extends AbstractEvent {

    @Override
    public String getType() {
        return "Basic";
    }

}
