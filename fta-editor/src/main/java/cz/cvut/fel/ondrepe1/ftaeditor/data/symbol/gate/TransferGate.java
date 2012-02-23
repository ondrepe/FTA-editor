package cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ondrepe
 */
@XmlRootElement(name="transferGate")
public class TransferGate extends AbstractGate {

    @Override
    public int getMinCountChildren() {
        return 0;
    }

    @Override
    public int getMaxCountChildren() {
        return 1;
    }

    @Override
    public String getType() {
        return "Transfer";
    }

}
