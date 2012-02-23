package cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ondrepe
 */
@XmlRootElement(name="majorityVoteGate")
public class MajorityVoteGate extends AbstractGate {

    @Override
    public int getMinCountChildren() {
        return 3;
    }

    @Override
    public String getType() {
        return "Majority Vote";
    }
    
    @Override
    public int getMaxCountChildren() {
        return APLICATION_MAX_CHILDREN;
    }
}
