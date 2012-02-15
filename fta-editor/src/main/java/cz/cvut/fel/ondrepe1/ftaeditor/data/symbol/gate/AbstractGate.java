package cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate;

import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ondrepe
 */
public abstract class AbstractGate extends AbstractSymbol {

    List<AbstractSymbol> children;

    public List<AbstractSymbol> getChildren() {
        if(children == null) children = new ArrayList<AbstractSymbol>();
        return children;
    }
    
    public void addChild(AbstractSymbol symbol) {
        if (canAddChild()) {
            symbol.setParent(this);
            getChildren().add(symbol);
        }
    }
    
    public boolean canAddChild() {
        if (getChildren().size() >= getMaxCountChildren()) return false;
        return true;
    }

    @Override
    protected void validateInternal() {
        if (getChildren().isEmpty()) {
            addErrorMessage("children", "no-children-error");
        } else if (getChildren().size() < getMinCountChildren()) {
            addErrorMessage("children", "not-enough-children-error");
        }
    }
    
    protected abstract int getMinCountChildren();
    protected abstract int getMaxCountChildren();
}
