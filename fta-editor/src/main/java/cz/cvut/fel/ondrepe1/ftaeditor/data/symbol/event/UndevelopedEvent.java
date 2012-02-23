package cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.event;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ondrepe
 */
@XmlRootElement(name="undevelopedEvent")
public class UndevelopedEvent extends AbstractEvent {

    @Override
    public String getType() {
        return "Nerozvíjená";
    }
}
