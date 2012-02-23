package cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ondrepe
 */
@XmlRootElement(name="priorityAndGate")
public class PriorityAndGate extends AbstractGate {

    @Override
    public int getMinCountChildren() {
        return 2;
    }

    @Override
    public String getType() {
        return "Priority AND";
    }
    
    @Override
    public int getMaxCountChildren() {
        return APLICATION_MAX_CHILDREN;
    }
}
