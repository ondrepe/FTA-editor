package cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate;

/**
 *
 * @author ondrepe
 */
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
