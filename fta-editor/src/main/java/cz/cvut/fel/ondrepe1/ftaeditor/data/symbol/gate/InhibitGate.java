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
    public void addChild(AbstractSymbol symbol) {
        if (getChildren().size() == 2) {
            //TODO: throw error
            return;
        }
        super.addChild(symbol);
    }
    
    @Override
    public String getType() {
        return "Inhibit";
    }

}
