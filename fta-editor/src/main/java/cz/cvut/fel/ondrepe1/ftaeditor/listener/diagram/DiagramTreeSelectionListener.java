package cz.cvut.fel.ondrepe1.ftaeditor.listener.diagram;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.event.SymbolSelectEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

/**
 *
 * @author ondrepe
 */
public class DiagramTreeSelectionListener implements TreeSelectionListener {

    public void valueChanged(TreeSelectionEvent e) {
        AbstractSymbol symbol = (AbstractSymbol) e.getNewLeadSelectionPath().getLastPathComponent();
        FtaController.getInstance().fireEvent(new SymbolSelectEvent(symbol));
    }

}
