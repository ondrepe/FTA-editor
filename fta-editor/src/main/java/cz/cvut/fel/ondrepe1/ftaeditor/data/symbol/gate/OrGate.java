package cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate;

/**
 *
 * @author ondrepe
 */
public class OrGate extends AbstractGate {

    @Override
    protected int getMinCountChildren() {
        return 2;
    }

    @Override
    public String getType() {
        return "OR";
    }
    
    @Override
    protected int getMaxCountChildren() {
        return Integer.MAX_VALUE;
    }
}
