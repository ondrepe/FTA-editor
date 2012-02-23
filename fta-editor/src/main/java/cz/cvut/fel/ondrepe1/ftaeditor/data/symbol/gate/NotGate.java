package cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ondrepe
 */
@XmlRootElement(name="notGate")
public class NotGate extends AbstractGate {

    @Override
    public int getMinCountChildren() {
        return 1;
    }
    
    @Override
    public String getType() {
        return "NOT";
    }
    
    @Override
    public int getMaxCountChildren() {
        return 1;
    }
}
