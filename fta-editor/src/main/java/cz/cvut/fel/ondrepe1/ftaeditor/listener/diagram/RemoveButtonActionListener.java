package cz.cvut.fel.ondrepe1.ftaeditor.listener.diagram;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.symbol.RemoveSymbolEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.symbol.SymbolSelectEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.symbol.ISymbolSelectListener;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author ondrepe
 */
public class RemoveButtonActionListener implements ActionListener, ISymbolSelectListener {

    private AbstractSymbol symbol;

    public RemoveButtonActionListener() {
        FtaController.getInstance().registerEventListener(SymbolSelectEvent.class, this);
    }
    
    public void actionPerformed(ActionEvent e) {
        FtaController.getInstance().fireEvent(new RemoveSymbolEvent(symbol));
    }

    public void onSelect(AbstractSymbol symbol) {
        this.symbol = symbol;
    }

}
