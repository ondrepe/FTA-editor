package cz.cvut.fel.ondrepe1.ftaeditor.listener.pallet;

import cz.cvut.fel.ondrepe1.ftaeditor.controller.FtaController;
import cz.cvut.fel.ondrepe1.ftaeditor.controller.api.event.symbol.PalletSymbolSelectEvent;
import cz.cvut.fel.ondrepe1.ftaeditor.data.symbol.AbstractSymbol;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import org.jdesktop.swingx.JXList;

/**
 *
 * @author ondrepe
 */
public class PalletSelectionListener implements ListSelectionListener {

    public void valueChanged(ListSelectionEvent e) {
        AbstractSymbol symbol = (AbstractSymbol) ((JXList)e.getSource()).getSelectedValue();
        try {
            symbol = (AbstractSymbol) symbol.clone();
        } catch (CloneNotSupportedException ex) {
        }
        FtaController.getInstance().fireEvent(new PalletSymbolSelectEvent(symbol));
    }

}
