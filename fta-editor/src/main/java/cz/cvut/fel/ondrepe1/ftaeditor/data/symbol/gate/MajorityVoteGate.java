package cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate;

/**
 *
 * @author ondrepe
 */
public class MajorityVoteGate extends AbstractGate {

    @Override
    protected int getMinCountChildren() {
        return 3;
    }

    @Override
    public String getType() {
        return "Majority Vote";
    }
    
    @Override
    protected int getMaxCountChildren() {
        return Integer.MAX_VALUE;
    }
}
