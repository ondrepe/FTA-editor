package cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.gate;

/**
 *
 * @author ondrepe
 */
public class InhibitGate extends AbstractGate {

    @Override
    public int getMinCountChildren() {
        return 2;
    }
    
    @Override
    public String getType() {
        return "Inhibit";
    }

    @Override
    public int getMaxCountChildren() {
        return 2;
    }
}
