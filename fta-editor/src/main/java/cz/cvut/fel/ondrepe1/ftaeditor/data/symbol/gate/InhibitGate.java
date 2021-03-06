package cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ondrepe
 */
@XmlRootElement(name="inhibitGate")
public class InhibitGate extends AbstractGate {

    @Override
    public int getMinCountChildren() {
        return 2;
    }
    
    @Override
    public String getType() {
        return "Inhibit";
    }

    @Override
    public int getMaxCountChildren() {
        return 2;
    }
}
