package cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate;

import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;

/**
 *
 * @author ondrepe
 */
public class InhibitGate extends AbstractGate {

    @Override
    protected int getMinCountChildren() {
        return 2;
    }
    
    @Override
    public String getType() {
        return "Inhibit";
    }

    @Override
    protected int getMaxCountChildren() {
        return 2;
    }
}
