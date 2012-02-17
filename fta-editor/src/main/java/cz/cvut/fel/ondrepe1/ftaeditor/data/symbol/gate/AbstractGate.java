package cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate;

import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;

/**
 *
 * @author ondrepe
 */
public abstract class AbstractGate extends AbstractSymbol {
    
    public static final int APLICATION_MAX_CHILDREN = 10;
    
    public abstract int getMinCountChildren();
    public abstract int getMaxCountChildren();
}
