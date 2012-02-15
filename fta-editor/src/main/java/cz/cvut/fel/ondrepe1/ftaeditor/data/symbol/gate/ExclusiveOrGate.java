package cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate;

/**
 *
 * @author ondrepe
 */
public class ExclusiveOrGate extends AbstractGate {

    @Override
    protected int getMinCountChildren() {
        return 2;
    }
    
    @Override
    public String getType() {
        return "XOR";
    }

}
