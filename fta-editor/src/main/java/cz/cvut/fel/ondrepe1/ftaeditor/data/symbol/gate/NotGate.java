package cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate;

import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;

/**
 *
 * @author ondrepe
 */
public class NotGate extends AbstractGate {

    @Override
    protected int getMinCountChildren() {
        return 1;
    }
    
    @Override
    public String getType() {
        return "NOT";
    }
    
    @Override
    protected int getMaxCountChildren() {
        return 1;
    }
}
