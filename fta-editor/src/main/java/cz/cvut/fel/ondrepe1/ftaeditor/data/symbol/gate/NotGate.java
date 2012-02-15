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
    public void addChild(AbstractSymbol symbol) {
        if (getChildren().size() == 1) {
            //TODO: throw exception
            return;
        }
        super.addChild(symbol);
    }
    
    @Override
    public String getType() {
        return "NOT";
    }
}
