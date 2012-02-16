package cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event;

import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;

/**
 *
 * @author ondrepe
 */
public class DataChangedEvent extends CommonEvent {

    private AbstractSymbol data;

    public DataChangedEvent(AbstractSymbol data) {
        this.data = data;
    }

    public AbstractSymbol getData() {
        return data;
    }
}
