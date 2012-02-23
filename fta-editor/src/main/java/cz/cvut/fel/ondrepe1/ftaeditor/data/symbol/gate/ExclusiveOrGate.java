package cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ondrepe
 */
@XmlRootElement(name="exclusiveOrEvent")
public class ExclusiveOrGate extends AbstractGate {

    @Override
    public int getMinCountChildren() {
        return 2;
    }
    
    @Override
    public String getType() {
        return "Exclusive OR";
    }

    @Override
    public int getMaxCountChildren() {
        return APLICATION_MAX_CHILDREN;
    }
}
